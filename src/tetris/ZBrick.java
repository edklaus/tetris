package tetris;
import java.awt.Color;
import java.awt.Point;

public class ZBrick extends Brick {
    
    int state = 0;
    
    public ZBrick() {
	color = new Color(51, 255, 153);
	points.add(new Point(0, 0));
	points.add(new Point(1, 0));
	points.add(new Point(1, 1));
	points.add(new Point(2, 1));
    }
    
    @Override
    public void rotate() {
	if (state == 0) {
	    state = 1;
	    points.get(0).y+=2;
	    points.get(3).x-=2;
	} else {
	    state = 0;
	    points.get(0).y-=2;
	    points.get(3).x+=2;
	}
    }

}
