import java.util.HashSet;
import java.util.Set;

public class HangmanGameLogic implements IHangmanGameLogic{


    private int _counterLostGuess = 0;
    private int _letterCounter = 0;
    private int _numberOfRejection;
    private IWordChooser _wc;
    private String[] _words;
    private Set<Character> _guess = new HashSet<Character>();
    private boolean _isFinished = false;

    public HangmanGameLogic(IWordChooser wordChooser, int numberOfRejection){
        _numberOfRejection = numberOfRejection;
        _wc = wordChooser;
        _words = _wc.getWord();
        countLetters(_words);
    }

    public String[] getWord() {
        return _words;
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

    public HangmanGameState guessLetter(char c){
        Boolean isSuccessToGuess = false;
        for(String text : _words){
            int index = text.indexOf(c);
            if (index >=0) {
                isSuccessToGuess = true;
                _guess.add(c);
                if(_guess.size() == _letterCounter)
                    _isFinished = true;
            }
        }
        return buildState(isSuccessToGuess);
    }

    private HangmanGameState buildState(Boolean isSuccessToGuess) {
        if(!isSuccessToGuess)
            _counterLostGuess++;
        if(_counterLostGuess == _numberOfRejection)
            _isFinished = true;
        return new HangmanGameState(_isFinished, _guess,isSuccessToGuess);
    }
}
