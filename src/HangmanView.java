import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

public class HangmanView extends JFrame implements IHangmanView{

    private int WIDTH = 600;
    private int HEIGHT = 400;

    private JLabel _counterGuessLbl;
    private JLabel _phraseLbl;
    private JComboBox _letterListOptionsCB;
    private int _counterGuess;
    private JPanel _hangmanDraw;

    public HangmanView(JPanel hangmanDraw){
        super("hangman");
        init();
        _hangmanDraw = hangmanDraw;

        add(_counterGuessLbl, BorderLayout.WEST);
        add(_phraseLbl, BorderLayout.NORTH);
        add(_hangmanDraw,BorderLayout.CENTER);
        add(_letterListOptionsCB, BorderLayout.SOUTH);

        setSize(WIDTH,HEIGHT);
        setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void init(){
        _counterGuess = 0;
        _letterListOptionsCB = buildLettersOptions('a','z');

        _phraseLbl = new JLabel(" ", SwingConstants.CENTER);
        _counterGuessLbl = new JLabel("Num of rejection = 0", SwingConstants.CENTER);
    }

    private JComboBox buildLettersOptions(char fromLetter,char toLetter) {

        int index = 0;
        String[] letters = new String[toLetter - fromLetter + 1];
        for(char letter = fromLetter; letter <= toLetter; letter++){
            index = letter - fromLetter;
            letters[index] = Character.toString(letter);
        }
        return new JComboBox(letters);
    }


    public void printOnScreen(String text) {
        _phraseLbl.setText(text);
        repaint();
    }

    public void increaseRejectionGuessCounter() {
        _counterGuessLbl.setText("Num of rejection = " + (++_counterGuess));
    }

    public void showMessage(String text) {
        JOptionPane.showConfirmDialog(null, text, text, JOptionPane.CLOSED_OPTION);
    }

    public void addElementToHangman() throws Exception {
        try {
            if(_hangmanDraw instanceof IIncreaseableNumberElementsToShow)
                ((IIncreaseableNumberElementsToShow)_hangmanDraw).increaseNumberOfElementToShow();
        }
        catch (Exception e){
            throw e;
        }
    }

    public void addGuessListener(ActionListener listener) {
        _letterListOptionsCB.addActionListener(listener);
    }
}
