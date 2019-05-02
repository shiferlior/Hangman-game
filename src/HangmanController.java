import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class HangmanController {

        private IHangmanView _hangmanView;
        private IHangmanGameLogic _hangmanGameLogic;

        public HangmanController(IHangmanView hangmanView, IHangmanGameLogic gl){
            _hangmanView = hangmanView;
            _hangmanGameLogic = gl;

            _hangmanView.addGuessListener(new lettersListener());
            pritnGuess(new HashSet());
        }

    private class lettersListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox)e.getSource();
            char letter = cb.getSelectedItem().toString().charAt(0);

            HangmanGameState hgState = _hangmanGameLogic.guessLetter(letter);
            if(!hgState.isSuccessToGuessLastTime())
                rejectGuess();
            pritnGuess(hgState.getGuess());
            if (hgState.getIsFinished())
                finishTheGame(hgState);
        }
    }

    private void rejectGuess() {
            try {
                _hangmanView.addElementToHangman();
            }
            catch (Exception ex){
                System.out.println(ex.toString());
            }
            _hangmanView.increaseRejectionGuessCounter();
    }

    private void pritnGuess(Set<Character> guesses)
    {
        String showOnScreen = "";
        for(String text : _hangmanGameLogic.getWord()) {
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

    private void finishTheGame(HangmanGameState hgState){
        String endGameMessage;
        if(!hgState.isSuccessToGuessLastTime())
            endGameMessage = "You Lose!!";
        else
            endGameMessage = "You Win!!";
        _hangmanView.printOnScreen(endGameMessage);
        _hangmanView.showMessage(endGameMessage);
        System.exit(0);
    }
}
