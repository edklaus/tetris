package tetris;
import java.awt.Color;
import java.awt.Point;

public class TBrick extends Brick {
    
    int state = 0;
    
    public TBrick() {
	color = new Color(255, 51, 51);
	points.add(new Point(1, 0));
	points.add(new Point(0, 1));
	points.add(new Point(1, 1));
	points.add(new Point(2, 1));
    }
    
    @Override
    public void rotate() {
	if (state == 0) {
	    state = 1;
	    points.get(1).y-=2;
	    points.get(2).x-=1;
	    points.get(2).y-=1;
	    points.get(3).x-=2;
	} else if (state == 1) {
	    state = 2;
	    points.get(1).x+=2;
	    points.get(2).x+=1;
	    points.get(2).y-=1;
	    points.get(3).y-=2;
	} else if (state == 2) {
	    state = 3;
	    points.get(1).y+=2;
	    points.get(2).x+=1;
	    points.get(2).y+=1;
	    points.get(3).x+=2;
	} else if (state == 3) {
	    state = 0;
	    points.get(1).x-=2;
	    points.get(2).x-=1;
	    points.get(2).y+=1;
	    points.get(3).y+=2;
	}
    }

}
