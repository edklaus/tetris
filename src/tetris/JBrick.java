package tetris;
import java.awt.Color;
import java.awt.Point;

public class JBrick extends Brick {

    int state = 0;

    public JBrick() {
	color = new Color(153, 255, 153);
	points.add(new Point(1, 0));
	points.add(new Point(1, 1));
	points.add(new Point(1, 2));
	points.add(new Point(0, 2));
    }

    @Override
    public void rotate() {
	if (state == 0) {
	    state = 1;
	    points.get(0).x += 1;
	    points.get(0).y += 1;
	    points.get(2).x -= 1;
	    points.get(2).y -= 1;
	    points.get(3).y -= 2;
	} else if (state == 1) {
	    state = 2;
	    points.get(0).x -= 1;
	    points.get(0).y += 1;
	    points.get(2).x += 1;
	    points.get(2).y -= 1;
	    points.get(3).x += 2;
	} else if (state == 2) {
	    state = 3;
	    points.get(0).x -= 1;
	    points.get(0).y -= 1;
	    points.get(2).x += 1;
	    points.get(2).y += 1;
	    points.get(3).y += 2;
	} else if (state == 3) {
	    state = 0;
	    points.get(0).x += 1;
	    points.get(0).y -= 1;
	    points.get(2).x -= 1;
	    points.get(2).y += 1;
	    points.get(3).x -= 2;
	}
    }

}
