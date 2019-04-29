

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class HangmanController {

//        class JTextFieldLimit extends PlainDocument {
//            private int limit;
//            JTextFieldLimit(int limit) {
//                super();
//                this.limit = limit;
//            }
//
//            JTextFieldLimit(int limit, boolean upper) {
//                super();
//                this.limit = limit;
//            }
//
//            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
//                if (str == null)
//                    return;
//
//                if ((getLength() + str.length()) <= limit) {
//                    super.insertString(offset, str, attr);
//                }
//            }
//        }

        private IHangmanModel _hangmanModel;
        private IHangmanView _hangmanView;
        private GameLogic _gl;

        public HangmanController(IHangmanView hangmanView,IHangmanModel hangmanModel,GameLogic gl){
            _hangmanView = hangmanView;
            _hangmanModel = hangmanModel;
            _gl = gl;

            _hangmanView.addGuessListener(new lettersListener());
        }

        private void pritnGuess(Set<Character> guesses)
        {
            String line = " ___ ";
            String showOnScreen = "";
            for(String text : _gl.getWord()) {
                for (char letter : text.toCharArray()) {
                    if(guesses.contains(letter))
                        showOnScreen += letter;
                    else
                        showOnScreen += line;
                }
                showOnScreen +="\t";
            }
            _hangmanView.printOnScreen(showOnScreen);
        }


    private class lettersListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox)e.getSource();
            char letter = (char)cb.getSelectedItem();

            _hangmanView.increaseGuessCounter();
//            char g;
//            if (e.getSource() == button) {
//                g = guess.getText().toCharArray()[0];
//            } else g = e.getActionCommand().toCharArray()[0];
//            guess.setText("");
            GamePackage gp = _gl.guessLetter(letter);
            pritnGuess(gp.guess);
            if (gp.finished) {
                _hangmanView.printOnScreen("");
                int answer = JOptionPane.showConfirmDialog(null, "you won!!! do you want another game?", "YOU WON!", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    _gl.resetGame();
                    pritnGuess(new HashSet<>());
                } else System.exit(0);
            }
        }
    }
}
