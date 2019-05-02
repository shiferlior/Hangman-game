import sun.launcher.resources.launcher;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class WordChooserFromFile extends Component implements IWordChooser {
    private String _filePath;
    private ArrayList<ArrayList<String>> _words;

    public WordChooserFromFile(){
        _words = new ArrayList<ArrayList<String>>();
        getPath();

    }

    private void getPath(){
        JFileChooser getFile = new JFileChooser();
        getFile.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = getFile.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            _filePath = getFile.getSelectedFile().getAbsolutePath();
            if(_filePath.endsWith("words.text"))
                extractWords(_filePath);
            else
                errorMessage("You must choose the file: words.text");
        }
        else
            errorMessage("You must choose the file: words.text");
    }


    @Override
    public String[] getWord() {
        return randomWord();
    }

    private void errorMessage(String text){
        JOptionPane.showConfirmDialog(null, text, "Error!", JOptionPane.CLOSED_OPTION);
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
            FileReader fr = new FileReader(_filePath);

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
            errorMessage("Problem with extract the words from the file!");
        }
    }
}
