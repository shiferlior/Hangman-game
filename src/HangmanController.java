

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class HangmanController {

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
            String line = " __ ";
            String showOnScreen = "";
            for(String text : _gl.getWord()) {
                for (char letter : text.toCharArray()) {
                    if(guesses.contains(letter))
                        showOnScreen += letter + " ";
                    else
                        showOnScreen += line;
                }
                showOnScreen +="      ";
            }
            _hangmanView.printOnScreen(showOnScreen);
        }


    private class lettersListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox)e.getSource();
            char letter = cb.getSelectedItem().toString().charAt(0);

            _hangmanView.increaseGuessCounter();

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
