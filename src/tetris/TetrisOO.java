package tetris;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TetrisOO {

    static List<Brick> bricks = new ArrayList<Brick>();
    static Brick currentBrick = null;

    static int scoreInt = 0;

    static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws Exception {

	// ############# BILDSCHIRMAUSGABE #############

	// Hauptfenster
	JFrame tetrisFrame = new JFrame("TETRIS");
	tetrisFrame.setMinimumSize(new Dimension(340, 450));
	tetrisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	tetrisFrame.setResizable(false);
	tetrisFrame.setLocationRelativeTo(null); // center on screen
	tetrisFrame.setVisible(true);

	// Spielfeld
	JPanel playingFieldContainer = new JPanel();
	playingFieldContainer.setBackground(Color.LIGHT_GRAY);
	playingFieldContainer.setLayout(new BoxLayout(playingFieldContainer, BoxLayout.PAGE_AXIS));
	JPanel playingField = new JPanel() {
	    @Override
	    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// bricks
		for (Brick brick : bricks) {
		    brick.draw(g);
		}
		// raster
		g.setColor(new Color(50, 50, 50));
		for (int i = 0; i < 200; i += 20) {
		    g.drawLine(i, 0, i, 400);
		}
		for (int i = 0; i < 400; i += 20) {
		    g.drawLine(0, i, 200, i);
		}
	    }
	};
	playingField.setMaximumSize(new Dimension(200, 400));
	playingField.setBackground(Color.DARK_GRAY);
	playingField.setAlignmentX(0.5f);
	playingFieldContainer.add(Box.createRigidArea(new Dimension(10, 10)));
	playingFieldContainer.add(playingField);
	tetrisFrame.add(playingFieldContainer, BorderLayout.CENTER);

	// Infobereich
	JPanel infoPanel = new JPanel();
	infoPanel.setBackground(Color.LIGHT_GRAY);
	infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
	infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 15));
	tetrisFrame.add(infoPanel, BorderLayout.EAST);

	// empty area
	infoPanel.add(Box.createRigidArea(new Dimension(10, 20)));

	// Punkte zählen
	JLabel scoreLabel = new JLabel("SCORE");
	scoreLabel.setAlignmentX(0.5f);
	infoPanel.add(scoreLabel);
	JLabel score = new JLabel("" + scoreInt);
	score.setAlignmentX(0.5f);
	infoPanel.add(score);

	// empty area
	infoPanel.add(Box.createRigidArea(new Dimension(10, 20)));

	// Lines zählen
	JLabel linesLabel = new JLabel("LINES");
	linesLabel.setAlignmentX(0.5f);
	infoPanel.add(linesLabel);
	JLabel lines = new JLabel("344");
	lines.setAlignmentX(0.5f);
	infoPanel.add(lines);

	// empty area
	infoPanel.add(Box.createRigidArea(new Dimension(10, 40)));

	// Nächster Block
	JPanel nextBlock = new JPanel();
	nextBlock.setMaximumSize(new Dimension(100, 100));
	nextBlock.setBackground(Color.DARK_GRAY);
	infoPanel.add(nextBlock);

	// empty area
	infoPanel.add(Box.createRigidArea(new Dimension(10, 110)));

	// Button für neues Spiel
	JButton newGameButton = new JButton("new game");
	newGameButton.setAlignmentX(0.5f);
	newGameButton.setFocusPainted(false);
	newGameButton.setContentAreaFilled(false);
	newGameButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.out.println("start new game");
		playingField.requestFocus();
	    }
	});
	infoPanel.add(newGameButton);

	// ############# TASTATUREINGABE #############
	playingField.requestFocus();
	playingField.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		    if (currentBrick.canMove(Brick.Direction.LEFT, bricks)) {
			currentBrick.move(Brick.Direction.LEFT);
		    }
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		    if (currentBrick.canMove(Brick.Direction.RIGHT, bricks)) {
			currentBrick.move(Brick.Direction.RIGHT);
		    }
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		    if (currentBrick.canMove(Brick.Direction.DOWN, bricks)) {
			currentBrick.move(Brick.Direction.DOWN);
		    }
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
		    currentBrick.rotate();
		}
	    }
	});

	// ############# GAMEPLAY #############
	int counter = 0;
	Brick b = null;
	b = new ZBrick();
	bricks.add(b);
	currentBrick = b;
	while (true) {
	    counter++;
	    if (counter == 25) {
		counter = 0;
		if (currentBrick.canMove(Brick.Direction.DOWN, bricks)) {
		    currentBrick.move(Brick.Direction.DOWN);
		} else {
		    // check for line
		    int linesRemoved = 0;
		    int lineCounter = 0;
		    List<Point> pointsToRemove = new ArrayList<>();
		    for (int i = 0; i < 20; i++) {
			for (Brick brick : bricks) {
			    for (Point point : brick.points) {
				if (point.y == i) {
				    lineCounter++;
				    pointsToRemove.add(point);
				}
			    }
			}
			if (lineCounter == 10) {
			    linesRemoved++;
			    for (Brick brick : bricks) {
				for (Point point : pointsToRemove) {
				    brick.points.remove(point);
				}
				for (Point point : brick.points) {
				    if (point.y < i) {
					point.y++;
				    }
				}
			    }
			}
			lineCounter = 0;
			pointsToRemove.clear();
		    }
		    if (linesRemoved == 1) {
			scoreInt = scoreInt + 40;
		    } else if (linesRemoved == 2) {
			scoreInt = scoreInt + 100;
		    } else if (linesRemoved == 3) {
			scoreInt = scoreInt + 300;
		    } else if (linesRemoved == 4) {
			scoreInt = scoreInt + 1200;
		    }
		    score.setText("" + scoreInt);
		    linesRemoved = 0;

		    // create new brick
		    int rand = random.nextInt(7);
		    if (rand == 0) {
			b = new ZBrick();
		    } else if (rand == 1) {
			b = new SBrick();
		    } else if (rand == 2) {
			b = new LBrick();
		    } else if (rand == 3) {
			b = new OBrick();
		    } else if (rand == 4) {
			b = new IBrick();
		    } else if (rand == 5) {
			b = new JBrick();
		    } else if (rand == 6) {
			b = new TBrick();
		    }
		    bricks.add(b);
		    currentBrick = b;
		}
	    }
	    tetrisFrame.repaint();
	    Thread.sleep(20);
	}

    }

}
