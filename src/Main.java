import javax.swing.*;

public class Main {
   
    public static void main(String[] args) {

        int numberOfRejection = 7;
        int BasicNumberElementsToDraw = 2;

        //You must search the file words.text
        IWordChooser wordChooser            = new WordChooserFromFile();
        IHangmanGameLogic hangmanGameLogic  = new HangmanGameLogic(wordChooser,numberOfRejection);

        JPanel hangmanDraw                  = new HangmanDraw(BasicNumberElementsToDraw);
        IHangmanView hangmanView            = new HangmanView(hangmanDraw);

        HangmanController hangmanController = new HangmanController(hangmanView,hangmanGameLogic);
    }
}
