import java.util.Random;

public class WordChooser implements IWordChooser {

    private String[][] _words;
    public WordChooser(){
        _words = this.loadWords();
    }

    private String[][] loadWords() {
        return null;
    }

    @Override
    public String[] getWord() {
        int rnd = new Random().nextInt(_words.length);
        return _words[rnd];
    }
}
