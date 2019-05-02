import java.awt.Graphics;
public class Line implements IDrawable {
    private int _x,_y,_x1,_y1;
    public Line(int x,int y,int x1, int y1){
        _x = x;
        _y = y;
        _x1 = x1;
        _y1 = y1;
    }
    public void draw(Graphics g){
        g.drawLine(_x,_y,_x1,_y1);
    }
}