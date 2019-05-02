import java.awt.Graphics;

public class Circle implements IDrawable {
    private int _x,_y,_r;

    public Circle(int x,int y,int r){
        _x = x;
        _y = y;
        _r = r;
    }

    public void draw(Graphics g) {
        int diameter = _r * 2;
        g.fillOval(_x - _r,_y - _r,diameter,diameter);
    }
}