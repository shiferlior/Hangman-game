
public class Main {
   
    public static void main(String[] args) {

        IHangmanView hangmanView = new HangmanView();
        HangmanController hangmanController = new HangmanController(hangmanView,null,new GameLogic());
//        ICalcModel      model      = new CalcModel();
//        ICalcView       view       = new CalcView();
//        ICalcController controller = new CalcController(model, view);
//
//        controller.run();
    }
}
