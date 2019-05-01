import java.util.HashSet;
import java.util.Set;

public class HangmanGameLogic {

    private IWordChooser _wc;

    private String[] _words;
    private Set<Character> _guess = new HashSet<Character>();
    private boolean _isFinished = false;
    private int _letterCounter = 0;

    public Set<Character> getGuess() {
        return _guess;
    }

    public String[] getWord() {
        return _words;
    }

    public HangmanGameLogic(){
        _wc = new WordMockChooser();
        _words = _wc.getWord();
        countLetters(_words);
    }

    private void countLetters(String word[])
    {
        Set<Character> set = new HashSet();
        for(String text : word)
        {
            for(char letter: text.toCharArray())
            {
                set.add(letter);
            }
        }
        _letterCounter = set.size();
    }

    public String[] resetGame(){
        _words = _wc.getWord();
        _guess = new HashSet();
        this.countLetters(_words);
        _isFinished = false;
        return _words;
    }

    public HangmanGameState guessLetter(char c){
        for(String text : _words){
            int index = text.indexOf(c);
            if (index >=0)
            {
                _guess.add(c);
                if(_guess.size() == _letterCounter)
                {
                    _isFinished = true;
                }
            }
        }
        return new HangmanGameState(_isFinished, _guess);
    }
}
