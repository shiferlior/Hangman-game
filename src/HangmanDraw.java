import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HangmanDraw extends JPanel implements IIncreaseableNumberElementsToShow{
    private ArrayList<IDrawable> _lines;
    private int _numberOfElementToShow;

    public HangmanDraw(int numberOfElementToShow) {
        _numberOfElementToShow = numberOfElementToShow;

        _lines = new ArrayList();
        _lines.add(new Line(30,30,300,30)); //Stand
        _lines.add(new Line(30,30,30,300)); //Ceiling
        _lines.add(new Line(165,30,165,60)); //Rope
        _lines.add(new Circle(165,80,20)); //Head
        _lines.add(new Line(165,100,165,180)); //Body
        _lines.add(new Line(165,180,180,230)); //Right leg
        _lines.add(new Line(165,180,150,230)); //Left leg
        _lines.add(new Line(165,140,190,180)); //Right hand
        _lines.add(new Line(165,140,140,180)); //Left hand
    }

    public void paintComponent(Graphics g){
        for(int i=0; i<_lines.size() && i < _numberOfElementToShow;i++){
            _lines.get(i).draw(g);
        }
    }

    public void increaseNumberOfElementToShow() throws Exception {
        _numberOfElementToShow++;
        if (_numberOfElementToShow > _lines.size())
            throw new Exception("You lose the game!");
        repaint();
    }






}
