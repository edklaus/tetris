package tetris;
import java.awt.Color;
import java.awt.Point;

public class OBrick extends Brick {
    
    int state = 0;
    
    public OBrick() {
	color = new Color(255, 255, 153);
	points.add(new Point(0, 0));
	points.add(new Point(0, 1));
	points.add(new Point(1, 0));
	points.add(new Point(1, 1));
    }
    
    @Override
    public void rotate() {

    }

}
