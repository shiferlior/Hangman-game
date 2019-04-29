import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class HangmanView extends JFrame implements ActionListener {

    class JTextFieldLimit extends PlainDocument {
        private int limit;
        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        JTextFieldLimit(int limit, boolean upper) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }

    private JLabel label;
    private JButton button;
    private JLabel text;
    private GameLogic gl;
    private JTextField guess;
    private int count = 0;

    public HangmanView(){
        super("hangman");
        text = new JLabel("", SwingConstants.CENTER);
        label = new JLabel("num of guesses = 0", SwingConstants.CENTER);
        button = new JButton("Enter");
        guess = new JTextField(10);
        guess.setBounds(40, 39, 105, 20);
        add(label, BorderLayout.SOUTH);
        add(button, BorderLayout.NORTH);
        add(text, BorderLayout.CENTER);
        add(guess, BorderLayout.WEST);
        button.addActionListener(this);
        guess.addActionListener(this);
        guess.setDocument(new JTextFieldLimit(1));
        setSize(600,400);
        setVisible(true);
        gl = new GameLogic();
        pritnGuess(new HashSet<>());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void pritnGuess(Set<Character> guesses)
    {
        String line = " ___ ";
        String screen = "";
        for(String text : gl.getWord())
        {
            for (char letter : text.toCharArray())
            {
                if(guesses.contains(letter))
                {
                    screen+=letter;
                }
                else screen +=line;
            }
            screen +="      ";
        }
        text.setText(screen);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("num of guesses = "+count);
        char g;
        if(e.getSource() ==button)
        {
            g=guess.getText().toCharArray()[0];
        }
        else g= e.getActionCommand().toCharArray()[0];
        guess.setText("");
        GamePackage gp = gl.guessLetter(g);
        pritnGuess(gp.guess);
        if(gp.finished)
        {
            text.setText("");
            int answer = JOptionPane.showConfirmDialog(null, "you won!!! do you want another game?","YOU WON!",JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION)
            {
                gl.resetGame();
                pritnGuess(new HashSet<>());
            }
            else System.exit(0);

        }

    }
}
