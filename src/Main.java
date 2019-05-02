import javax.swing.*;

public class Main {
   
    public static void main(String[] args) {

        int numberOfRejection = 7;
        int BasicNumberElementsToDraw = 2;

        JPanel hangmanDraw                  = new HangmanDraw(BasicNumberElementsToDraw);
        IHangmanView hangmanView            = new HangmanView(hangmanDraw);

        IWordChooser wordChooser            = new WordChooserFromFile();
        IHangmanGameLogic hangmanGameLogic  = new HangmanGameLogic(wordChooser,numberOfRejection);

        HangmanController hangmanController = new HangmanController(hangmanView,hangmanGameLogic);

    }
}
