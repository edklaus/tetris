package tetris;
import java.awt.Color;
import java.awt.Point;

public class IBrick extends Brick {
    
    int state = 0;
    
    public IBrick() {
	color = new Color(200, 200, 200);
	points.add(new Point(0, 0));
	points.add(new Point(0, 1));
	points.add(new Point(0, 2));
	points.add(new Point(0, 3));
    }
    
    @Override
    public void rotate() {
	if (state == 0) {
	    state = 1;
	    points.get(0).x++;
	    points.get(0).y++;
	    points.get(2).x--;
	    points.get(2).y--;
	    points.get(3).x-=2;
	    points.get(3).y-=2;
	} else {
	    state = 0;
	    points.get(0).x--;
	    points.get(0).y--;
	    points.get(2).x++;
	    points.get(2).y++;
	    points.get(3).x+=2;
	    points.get(3).y+=2;
	}
    }

}
