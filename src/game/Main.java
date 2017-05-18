package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;

import menu.GameOver;
import menu.Menu;

/**
 * This is the main class that is used to run the whole game and has the
 * functionality of the zombies, bullets, player and etc. It is used to access
 * the other classes either as an array or as its own entity.
 * 
 * @author Stefan Mitic, Alex Chan, Sowon Ham
 */

public class Main extends Game {
	private Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
	private Borders b1, b2, b3, b4, b5, b6;
	private final int BORDERS_WIDTH = 30;
	private final Color WALL_COLOR = Color.black;
	private final int SPEED = 5;
	private Player p;
	private Bullets[] bulletArr = new Bullets[10000];
	private int bulletCounter = 0;
	private boolean upDirection = false;
	private boolean downDirection = false;
	private boolean leftDirection = false;
	private boolean rightDirection = false;
	private ZombieType[] z = new ZombieType[10000];
	private int zombieCounter = 0, roundSpawn, round = 1, roundKilled = 0,
			roundEnd = 0;
	private boolean roundStart = true;
	private JLabel scoreLabel;

	/**
	 * The default constructor for the main game.
	 */
	public Main() {
		setVisible(true);
		initComponents();
	}

	@Override
	/**
	 * The act method is called every millisecond while
	 * the game is running.
	 */
	public void act() {
		zombies();

		if (!roundStart) {
			playerHealth();
			playerMovement();
			bullets();
			zombieCollision();
			playerLocation();
			zombieBulletCollision();
			zombieHealthDetection();
		}
		setDelay(15);
	}

	@Override
	/** The setup of the game where it sets up the players, maps, and scores, etc. */
	public void setup() {
		// Game setup
		player();
		mapSetup();
		roundSpawn = round * 5;
		score();
	}

	/** The method for setting up the player. */
	public void player() {
		// The player setup
		p = new Player();
		p.start();
		add(p);
	}

	/**
	 * Sets up the score board for both players and checks for the winner
	 */
	public void score() {
		scoreLabel = new JLabel();
		scoreLabel.setText("Round: " + round);
		scoreLabel.setBounds(20, 20, 300, 40);
		scoreLabel.setFont(new Font("Serif", Font.PLAIN, 40));
		scoreLabel.setForeground(Color.BLACK);
		add(scoreLabel);
		repaint();
	}

	/**
	 * The method for score changing when another highscore is reached with a
	 * different player.
	 */
	public void scoreChange() {
		remove(scoreLabel);
		score();
	}

	/**
	 * The method for map setup where it sets the borders that zombies and
	 * players cannot go through.
	 */
	public void mapSetup() {
		b1 = new Borders();
		add(b1);
		b1.setColor(WALL_COLOR);
		b1.setX((int) s.getWidth() / 2 - (int) s.getWidth() / 5);
		b1.setY((int) s.getHeight() / 5);
		b1.setSize(
				BORDERS_WIDTH,
				(int) s.getHeight() / 2 - ((int) s.getHeight() / 5)
						- ((int) s.getHeight() / 20));

		b2 = new Borders();
		add(b2);
		b2.setColor(WALL_COLOR);
		b2.setX((int) s.getWidth() / 2 - (int) s.getWidth() / 5);
		b2.setY((int) s.getHeight() / 2 + (int) s.getHeight() / 20);
		b2.setSize(
				BORDERS_WIDTH,
				(int) s.getHeight() / 2 - ((int) s.getHeight() / 5)
						- ((int) s.getHeight() / 20));

		b3 = new Borders();
		add(b3);
		b3.setColor(WALL_COLOR);
		b3.setX((int) s.getWidth() / 2 + (int) s.getWidth() / 5 - BORDERS_WIDTH);
		b3.setY((int) s.getHeight() / 5);
		b3.setSize(BORDERS_WIDTH, (int) s.getHeight() / 5 * 3);

		b4 = new Borders();
		add(b4);
		b4.setColor(WALL_COLOR);
		b4.setX((int) s.getWidth() / 2 - (int) s.getWidth() / 5);
		b4.setY((int) s.getHeight() / 5);
		b4.setSize((int) s.getWidth() / 2 - (int) s.getWidth() / 10 - 1,
				BORDERS_WIDTH);

		b5 = new Borders();
		add(b5);
		b5.setColor(WALL_COLOR);
		b5.setX((int) s.getWidth() / 2 - (int) s.getWidth() / 5);
		b5.setY((int) s.getHeight() / 5 * 4);
		b5.setSize((int) s.getWidth() / 5 - (int) s.getWidth() / 30,
				BORDERS_WIDTH);

		b6 = new Borders();
		add(b6);
		b6.setColor(WALL_COLOR);
		b6.setX((int) s.getWidth() / 2 + (int) s.getWidth() / 30);
		b6.setY((int) s.getHeight() / 5 + (int) s.getHeight() / 5 * 3);
		b6.setSize((int) s.getWidth() / 5 - (int) s.getWidth() / 30,
				BORDERS_WIDTH);
	}

	/**
	 * The method for allowing directions to reset for players and bullet
	 * directions.
	 */
	public void directionReset() {
		upDirection = false;
		downDirection = false;
		leftDirection = false;
		rightDirection = false;
		p.setUp(false);
		p.setDown(false);
		p.setRight(false);
		p.setLeft(false);
	}

	/**
	 * The movement detection for player when WASD keys are pressed with a final
	 * speed.
	 */
	public void playerMovement() {
		int x = p.getX();
		int y = p.getY();

		if (WKeyPressed() || SKeyPressed() || DKeyPressed() || AKeyPressed()) {
			directionReset();
			if (WKeyPressed() && !AKeyPressed() && !DKeyPressed()
					&& !SKeyPressed()) {
				p.setUp(true);
				p.setY(p.getY() - SPEED);
				upDirection = true;
			} else if (SKeyPressed() && !AKeyPressed() && !DKeyPressed()
					&& !WKeyPressed()) {
				p.setDown(true);
				p.setY(p.getY() + SPEED);
				downDirection = true;
			}
			if (WKeyPressed() && SKeyPressed()) {
				upDirection = true;
				downDirection = false;
			}
			if (AKeyPressed()) {
				p.setLeft(true);
				p.setX(p.getX() - SPEED);
				leftDirection = true;
				if (WKeyPressed()) {
					p.setUp(true);
					p.setY(p.getY() - SPEED);
					upDirection = true;
				} else if (SKeyPressed()) {
					p.setDown(true);
					p.setY(p.getY() + SPEED);
					downDirection = true;
				}
			} else if (DKeyPressed()) {
				p.setRight(true);
				p.setX(p.getX() + SPEED);
				rightDirection = true;
				if (WKeyPressed()) {
					p.setUp(true);
					p.setY(p.getY() - SPEED);
					upDirection = true;
				} else if (SKeyPressed()) {
					p.setDown(true);
					p.setY(p.getY() + SPEED);
					downDirection = true;
				}
			}
		}

		// The ability for the player so that they can't go through walls.
		if (p.collides(b1) || p.collides(b2) || p.collides(b3)
				|| p.collides(b4) || p.collides(b5) || p.collides(b6)) {
			p.setX(x);
			p.setY(y);
		}
	}

	/**
	 * The mechanics behind the arrays of bullets and direction detection of the
	 * bullets firing.
	 */
	public void bullets() {
		// The main function for when "Space Bar" is pressed the bullet is
		// fired.
		if (spaceKeyPressed()) {
			bulletCounter++;
			setSpaceKeyPressed(false);
			bulletArr[bulletCounter] = new Bullets();
			add(bulletArr[bulletCounter]);
			bulletArr[bulletCounter].setX(p.getX() + 20);
			bulletArr[bulletCounter].setY(p.getY() + 20);

			if (leftDirection) {
				bulletArr[bulletCounter].setMoveLeft(true);
			}
			if (rightDirection) {
				bulletArr[bulletCounter].setMoveRight(true);
			}
			if (upDirection) {
				bulletArr[bulletCounter].setMoveUp(true);
			}
			if (downDirection) {
				bulletArr[bulletCounter].setMoveDown(true);
			}
		}

		// The loop for where if the bullets collides with any wall it's going
		// remove it.
		for (int counter = 1; counter <= bulletCounter; counter++) {
			if (bulletArr[counter].collides(b1)
					|| bulletArr[counter].collides(b2)
					|| bulletArr[counter].collides(b3)
					|| bulletArr[counter].collides(b4)
					|| bulletArr[counter].collides(b5)
					|| bulletArr[counter].collides(b6)) {
				remove(bulletArr[counter]);
			}
		}
	}

	/**
	 * The health detection for the player when it collides with any type of
	 * zombies.
	 */
	public void playerHealth() {
		// If the zombie collides with the player the game stops and goes back
		// to the main menu.
		for (zombieCounter = roundEnd; zombieCounter < roundSpawn; zombieCounter++) {
			if (p.collides(z[zombieCounter])) {
				new Menu();
				GameOver gameOver = new GameOver();
				gameOver.setRound(round);
				setVisible(false);
				stopGame();
			}
		}
	}

	/**
	 * The actual spawning of zombies and the locations called from another
	 * class and round display function.
	 */
	public void zombies() {
		// The random zombie spawning when the actual game starts.
		if (roundStart) {
			for (zombieCounter = roundEnd; zombieCounter < roundSpawn; zombieCounter++) {
				z[zombieCounter] = new ZombieType();
				add(z[zombieCounter]);
				z[zombieCounter].zombieSpawning();
			}
			roundStart = false;
		}

		// The score change when each of the separate rounds are over.
		if (roundKilled >= roundSpawn) {
			round++;
			roundEnd = roundKilled;
			roundSpawn += round * 5;
			roundStart = true;
			scoreChange();
		}

	}

	/** This method allows for the zombie to get the location of the player. */
	public void playerLocation() {
		for (zombieCounter = roundEnd; zombieCounter < roundSpawn; zombieCounter++) {
			z[zombieCounter].playerX(p.getX());
			z[zombieCounter].playerY(p.getY());
		}
	}

	/**
	 * The method for zombie's health decrease and recoil function when bullet
	 * collides with zombies.
	 */
	public void zombieBulletCollision() {
		for (int i = 1; i <= bulletCounter; i++) {
			for (int j = roundEnd; j < roundSpawn; j++) {
				if (bulletArr[i].collides(z[j])) {
					remove(bulletArr[i]);
					z[j].healthDecrease();

					if (bulletArr[i].isMoveUp()) {
						// The bullet stays where the zombie is dead therefore,
						// moved the bullet to off the screen.
						bulletArr[i].setX(-200);
						z[j].upRecoil();
					}
					if (bulletArr[i].isMoveDown()) {
						// The bullet stays where the zombie is dead therefore,
						// moved the bullet to off the screen.
						bulletArr[i].setX(-200);
						z[j].downRecoil();
					}
					if (bulletArr[i].isMoveRight()) {
						// The bullet stays where the zombie is dead therefore,
						// moved the bullet to off the screen.
						bulletArr[i].setX(-200);
						z[j].rightRecoil();
					}
					if (bulletArr[i].isMoveLeft()) {
						// The bullet stays where the zombie is dead therefore,
						// moved the bullet to off the screen.
						bulletArr[i].setX(-200);
						z[j].leftRecoil();
					}
				}
			}
		}
	}

	/** The health detection for zombies where each bullet counts as one damage. */
	public void zombieHealthDetection() {
		for (int i = roundEnd; i < roundSpawn; i++) {
			if (z[i].healthDetection()) {
				remove(z[i]);
				roundKilled++;
			}
		}
	}

	/** The method for zombie collision where they cannot overlap each other. */
	public void zombieCollision() {
		for (int i = roundEnd; i < roundSpawn; i++) {
			// The wall collision detection for zombies so zombies don't go
			// through them.
			if (z[i].collides(b1) || z[i].collides(b2) || z[i].collides(b3)) {
				// When the bullets are fired the recoil is 0 so that zombies
				// don't go through walls.
				z[i].setRecoil(0);
				z[i].verticalWallCollision();
			} else if (z[i].collides(b4) || z[i].collides(b5)
					|| z[i].collides(b6)) {
				z[i].setRecoil(0);
				z[i].horizontalWallCollision();
			} else if (z[i].getX() <= 0
					|| z[i].getX() >= s.getWidth() - z[i].getWidth()
					|| z[i].getY() <= 0
					|| z[i].getY() >= s.getHeight() - z[i].getHeight()) {
				z[i].setRecoil(0);
			} else {
				z[i].resetRecoil();
			}

			// The collision within the zombies so they don't go through each
			// other/ overlap.
			for (int j = i + 1; j < roundSpawn; j++) {
				if (z[j].collides(z[i])) {
					z[j].verticalWallCollision();
					z[j].horizontalWallCollision();
				}
			}
		}
	}
}
