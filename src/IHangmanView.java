import java.awt.event.ActionListener;
import java.util.Set;

public interface IHangmanView {
    void printOnScreen(String text);
    void increaseGuessCounter();
    void addGuessListener(ActionListener listener);

}
