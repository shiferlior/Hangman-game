import java.util.Set;

public class HangmanGameState {
    private boolean _isFinished = false;
    private Set<Character> _guess;

    public HangmanGameState(boolean finished, Set<Character> guess)
    {
        _isFinished = finished;
        _guess = guess;
    }

    public boolean getIsFinished(){
        return _isFinished;
    }

    public Set<Character> getGuess(){
        return _guess;
    }
}
