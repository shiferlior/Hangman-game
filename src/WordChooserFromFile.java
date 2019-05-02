import sun.launcher.resources.launcher;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class WordChooserFromFile extends Component implements IWordChooser {
    private ArrayList<ArrayList<String>> _words;

    public WordChooserFromFile(){
        _words = new ArrayList<ArrayList<String>>();
    }
    @Override
    public String[] getWord() {

        String filePath;
        JFileChooser getFile = new JFileChooser();
        getFile.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = getFile.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            filePath = getFile.getSelectedFile().getAbsolutePath();
            if(filePath.endsWith("words.text"))
                extractWords(filePath);
            else
                errorMessage();
        }
        else
            errorMessage();
        return randomWord();
    }

    private void errorMessage(){
        JOptionPane.showConfirmDialog(null, "You must choose the file: words.text", "Error!", JOptionPane.CLOSED_OPTION);
        System.exit(0);
    }

    private String[] randomWord(){
        Random rnd = new Random();
        int index = rnd.nextInt(_words.size());
        return _words.get(index).toArray(new String[_words.get(index).size()]);
    }

    private void extractWords(String path){
        int i;
        String word = "";
        ArrayList<String> phrase = new ArrayList<String>();
        try {
            FileReader fr = new FileReader("/Users/l/Desktop/words.text");

            while ((i = fr.read()) != -1) {
                if ('a' <= i && i <= 'z') {
                    word += Character.toString((char) i);
                } else if ('A' <= i && i <= 'Z') {
                    word += Character.toString((char) (i - ('A' - 'a')));
                } else if ((char) i == ' ') {
                    phrase.add(word);
                    word = "";
                } else if (i == ',' || i == '.') {
                    phrase.add(word);
                    _words.add(phrase);
                    phrase = new ArrayList<String>();
                    word = "";
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
