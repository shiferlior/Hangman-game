

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class HangmanController {

        private IHangmanModel _hangmanModel;
        private IHangmanView _hangmanView;
        private HangmanGameLogic _gl;

        public HangmanController(IHangmanView hangmanView, IHangmanModel hangmanModel, HangmanGameLogic gl){
            _hangmanView = hangmanView;
            _hangmanModel = hangmanModel;
            _gl = gl;

            _hangmanView.addGuessListener(new lettersListener());
            pritnGuess(new HashSet());
        }

        private void pritnGuess(Set<Character> guesses)
        {
            String showOnScreen = "";
            for(String text : _gl.getWord()) {
                for (char letter : text.toCharArray()) {
                    if(guesses.contains(letter))
                        showOnScreen += letter + " ";
                    else
                        showOnScreen += " __ ";
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

            
            try {
                _hangmanView.addElementToHangman();
            }
            catch (Exception ex){
                _hangmanView.printOnScreen(ex.getMessage());
            }

            HangmanGameState gp = _gl.guessLetter(letter);
            pritnGuess(gp.getGuess());
            if (gp.getIsFinished()) {
                _hangmanView.printOnScreen("");
                int answer = JOptionPane.showConfirmDialog(null, "you won!!! do you want another game?", "YOU WON!", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    _gl.resetGame();
                    pritnGuess(new HashSet());
                } else System.exit(0);
            }
        }
    }
}
