package tetris;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class Brick {

    Color color = Color.WHITE;
    List<Point> points = new ArrayList<Point>();

    public enum Direction {
	LEFT, RIGHT, DOWN
    }

    public void draw(Graphics g) {
	g.setColor(color);
	for (Point point : points) {
	    g.fillRect(point.x * 20, point.y * 20, 20, 20);
	}
    }

    public boolean canMove(Direction direction, List<Brick> bricks) {
	// borders
	if (direction == Direction.LEFT) {
	    for (Point point : points) {
		if (point.x == 0) {
		    return false;
		}
	    }
	}
	if (direction == Direction.RIGHT) {
	    for (Point point : points) {
		if (point.x == 9) {
		    return false;
		}
	    }
	}
	if (direction == Direction.DOWN) {
	    for (Point point : points) {
		if (point.y == 19) {
		    return false;
		}
	    }
	}
	// other bricks
	for (Brick brick : bricks) {
	    if (!brick.equals(this)) {
		for (Point point1 : brick.points) {
		    for (Point point2 : points) {
			if (direction == Direction.LEFT) {
			    if (point1.x == point2.x-1 && point1.y == point2.y) {
				return false;
			    }
			}
			if (direction == Direction.RIGHT) {
			    if (point1.x == point2.x+1 && point1.y == point2.y) {
				return false;
			    }
			}
			if (direction == Direction.DOWN) {
			    if (point1.x == point2.x && point1.y == point2.y+1) {
				return false;
			    }
			}
		    }
		}
	    }
	}
	return true;
    }

    public void move(Direction direction) {
	if (direction == Direction.LEFT) {
	    for (Point point : points) {
		point.x--;
	    }
	}
	if (direction == Direction.RIGHT) {
	    for (Point point : points) {
		point.x++;
	    }
	}
	if (direction == Direction.DOWN) {
	    for (Point point : points) {
		point.y++;
	    }
	}
    }
    
    public abstract void rotate();

}
