package tetris;
import java.awt.Color;
import java.awt.Point;

public class SBrick extends Brick {
    
    int state = 0;
    
    public SBrick() {
	color = new Color(255, 153, 153);
	points.add(new Point(0, 1));
	points.add(new Point(1, 0));
	points.add(new Point(1, 1));
	points.add(new Point(2, 0));
    }
    
    @Override
    public void rotate() {
	if (state == 0) {
	    state = 1;
	    points.get(0).x+=2;
	    points.get(3).y+=2;
	} else {
	    state = 0;
	    points.get(0).x-=2;
	    points.get(3).y-=2;
	}
    }

}
