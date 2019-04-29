import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GameLogic {

    private IWordChooser wc;

    private String[] word;
    Set<Character> guess = new HashSet<Character>();
    private boolean finished = false;
    private int letterCounter = 0;

    public Set<Character> getGuess() {
        return guess;
    }

    public String[] getWord() {
        return word;
    }

    public GameLogic(){
        wc = new WordMockChooser();
        word = wc.getWord();
        this.countLetters(word);
    }

    private void countLetters(String word[])
    {
        Set<Character> set = new HashSet<Character>();
        for(String text : word)
        {
            for(char letter: text.toCharArray())
            {
                set.add(letter);
            }
        }
        letterCounter = set.size();
    }

    public String[] resetGame(){
        word = wc.getWord();
        guess = new HashSet<Character>();
        this.countLetters(word);
        finished = false;
        return word;
    }

    public GamePackage guessLetter(char c){
        for(String text : word){
            int index = text.indexOf(c);
            if (index >=0)
            {
                guess.add(c);
                if(guess.size() == letterCounter)
                {
                    finished = true;
                }
            }
        }
        return new GamePackage(finished, guess);
    }
}
