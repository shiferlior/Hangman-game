import java.util.Set;

public class GamePackage {
    public boolean finished = false;
    Set<Character> guess;

    GamePackage(boolean finished, Set<Character> guess)
    {
        this.finished = finished;
        this.guess = guess;
    }
}
