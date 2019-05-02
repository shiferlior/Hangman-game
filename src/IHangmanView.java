import java.awt.event.ActionListener;

public interface IHangmanView {
    void printOnScreen(String text);
    void increaseRejectionGuessCounter();
    void showMessage(String text);
    void addGuessListener(ActionListener listener);
    void addElementToHangman() throws Exception;

}
