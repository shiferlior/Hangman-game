import java.util.Set;

public class HangmanGameState {
    private boolean _isFinished = false;
    private boolean _isSuccessToGuessLastTime = false;
    private Set<Character> _guess;

    public HangmanGameState(boolean finished, Set<Character> guess,Boolean isSuccessToGuessLastTime)
    {
        _isFinished = finished;
        _guess = guess;
        _isSuccessToGuessLastTime = isSuccessToGuessLastTime;
    }

    public boolean getIsFinished(){
        return _isFinished;
    }

    public Set<Character> getGuess(){
        return _guess;
    }

    public Boolean isSuccessToGuessLastTime() {
        return _isSuccessToGuessLastTime;
    }
}
