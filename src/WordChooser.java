import java.util.Random;

public class WordChooser implements IWordChooser {

    private String[][] words;
    public WordChooser(){
        this.words = this.loadWords();
    }

    private String[][] loadWords()
    {
        return null;
    }

    @Override
    public String[] getWord() {
        int rnd = new Random().nextInt(this.words.length);
        return this.words[rnd];
    }
}
