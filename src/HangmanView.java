import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class HangmanView extends JFrame implements IHangmanView{


    private JLabel _counterGuessLbl;
    private JLabel _phraseLbl;
    private JComboBox _letterListOptionsCB;
    private int _counterGuess;

    public HangmanView(){
        super("hangman");
        _counterGuess = 0;
        _letterListOptionsCB = buildLettersOptions('a','z');

        _phraseLbl = new JLabel("", SwingConstants.CENTER);
        _counterGuessLbl = new JLabel("num of guesses = 0", SwingConstants.CENTER);

        add(_counterGuessLbl, BorderLayout.SOUTH);
        add(_phraseLbl, BorderLayout.CENTER);
        add(_letterListOptionsCB, BorderLayout.WEST);

        setSize(600,400);
        setVisible(true);

        printOnScreen("");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    @Override
    public void printOnScreen(String text)
    {
        _phraseLbl.setText(text);
    }

    @Override
    public void increaseGuessCounter()
    {
        _counterGuessLbl.setText("num of guesses = " + (++_counterGuess));
    }

    public void addGuessListener(ActionListener listener) {
        _letterListOptionsCB.addActionListener(listener);
    }


}
