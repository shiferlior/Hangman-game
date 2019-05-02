public interface IHangmanGameLogic {
    String[] getWord();
    HangmanGameState guessLetter(char c);
}
