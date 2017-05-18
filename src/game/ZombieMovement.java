package game;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * The class that controls the functionality of the zombie movements. Also known
 * as the AI for the enemy, detects the fastest path to get from the enemy's
 * location to the player's location.
 * 
 * @author Stefan Mitic, Alex Chan, Sowon Ham
 */

public class ZombieMovement extends GameObject {
	private Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
	private int xCoordinate, yCoordinate, ax, ay, bx, by, dx, dy, px, py,
			xLocation, yLocation, quadrant, playerQuadrant, zombieQuadrant;
	private int speed, xSpeed, ySpeed, recoil = 20;
	private boolean player, zombie, attack, tr, tl, tlu, tld, br, bl, el, eb;
	private boolean verticalCollision = false, horizontalCollision = false;

	@Override
	/**
	 * Act method that is called upon every millisecond.
	 */
	public void act() {
		playerLocation();
		zombieLocation();
		movement();
	}

	/**
	 * The method for where the zombies spawn where they spawn outside of the
	 * screen.
	 */
	public void zombieSpawnPoint() {
		int spawnPoint = (int) (Math.random() * 4);
		switch (spawnPoint) {
		case 0:
			setX((int) (Math.random() * s.getWidth()));
			setY(-100);
			break;
		case 1:
			setX((int) (Math.random() * s.getWidth()));
			setY((int) s.getHeight() + 100);
			break;
		case 2:
			setX(-100);
			setY((int) (Math.random() * s.getHeight()));
			break;
		case 3:
			setX((int) s.getWidth() + 100);
			setY((int) (Math.random() * s.getHeight()));
			break;
		}
	}

	/**
	 * The method for where it calls the methods within the class to perform the
	 * actions within the zombies.
	 */
	public void setup() {
		xSpeed = speed;
		ySpeed = speed;
		ax = (int) (s.getWidth() / 2 - s.getWidth() / 5 - getWidth() - 5);
		ay = (int) (s.getHeight() / 5 - getHeight() - 1);
		bx = (int) (s.getWidth() / 2 + s.getWidth() / 5);
		by = (int) (s.getHeight() / 5 * 4 + 30);
		player = false;
		zombie = false;
		reset();
		zombieSpawnPoint();
	}

	/** The method for where it detects the player location in the game. */
	public void playerLocation() {
		xLocation = px;
		yLocation = py;
		player = true;
		entityLocation();
	}

	/** The method for where it detects the zombie location in the game. */
	public void zombieLocation() {
		xLocation = getX();
		yLocation = getY();
		zombie = true;
		entityLocation();
	}

	/**
	 * The method for where it detects the entity location in the game separated
	 * by quadrants.
	 */
	public void entityLocation() {
		if (yLocation <= ay + 3) {
			if (xLocation <= ax) {
				quadrant = 1;
			} else if (xLocation > ax && xLocation <= bx) {
				quadrant = 2;
			} else if (xLocation > bx) {
				quadrant = 3;
			}
		} else if (yLocation > ay && yLocation <= by) {
			if (xLocation <= ax + 3) {
				quadrant = 8;
			} else if (xLocation > ax && xLocation <= bx) {
				quadrant = 9;
			} else if (xLocation > bx) {
				quadrant = 4;
			}
		} else if (yLocation > by) {
			if (xLocation <= ax) {
				quadrant = 7;
			} else if (xLocation > ax && xLocation <= bx) {
				quadrant = 6;
			} else if (xLocation > bx) {
				quadrant = 5;
			}
		}
		if (player) {
			playerQuadrant = quadrant;
			player = false;
		}
		if (zombie) {
			zombieQuadrant = quadrant;
			zombie = false;
		}
	}

	/**
	 * The method for where it contains many switch statements detecting the
	 * player quadrant and zombie quadrant to figure out which would be the
	 * fastest route for the zombie to take to reach the player (the map is
	 * separated into 9 quadrants).
	 */
	public void directionDecision() {
		switch (playerQuadrant) {
		case 1:
			switch (zombieQuadrant) {
			case 1:
			case 2:
			case 3:
			case 7:
			case 8:
				moveToPlayer();
				break;
			case 5:
			case 6:
				moveToBottomLeft();
				break;
			case 4:
				moveToTopRight();
				break;
			case 9:
				moveToLeftEntrance();
				break;
			}
			break;
		case 2:
			switch (zombieQuadrant) {
			case 1:
			case 2:
			case 3:
				moveToPlayer();
				break;
			case 4:
			case 5:
				moveToTopRight();
				break;
			case 6:
				moveToBottomLeft();
				break;
			case 7:
			case 8:
				tlu = true;
				moveToTopLeft();
				break;
			case 9:
				moveToLeftEntrance();
				break;
			}
			break;
		case 3:
			switch (zombieQuadrant) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				moveToPlayer();
				break;
			case 6:
				moveToBottomRight();
				break;
			case 7:
			case 8:
				tlu = true;
				moveToTopLeft();
				break;
			case 9:
				moveToLeftEntrance();
				break;
			}
			break;
		case 4:
			switch (zombieQuadrant) {
			case 1:
			case 2:
				moveToTopRight();
				break;
			case 3:
			case 4:
			case 5:
				moveToPlayer();
				break;
			case 6:
			case 7:
				moveToBottomRight();
				break;
			case 8:
				moveToBottomLeft();
				break;
			case 9:
				moveToBottomEntrance();
				break;
			}
			break;
		case 5:
			switch (zombieQuadrant) {
			case 1:
			case 8:
				moveToBottomLeft();
				break;
			case 2:
				moveToTopRight();
				break;
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				moveToPlayer();
				break;
			case 9:
				moveToBottomEntrance();
				break;
			}
			break;
		case 6:
			switch (zombieQuadrant) {
			case 1:
			case 8:
				moveToBottomLeft();
				break;
			case 2:
				tld = true;
				moveToTopLeft();
				break;
			case 3:
			case 4:
				moveToBottomRight();
				break;
			case 5:
			case 6:
			case 7:
				moveToPlayer();
				break;
			case 9:
				moveToBottomEntrance();
				break;
			}
			break;
		case 7:
			switch (zombieQuadrant) {
			case 1:
			case 8:
			case 7:
			case 6:
			case 5:
				moveToPlayer();
				break;
			case 2:
			case 3:
				tlu = true;
				moveToTopLeft();
				break;
			case 4:
				moveToBottomRight();
				break;
			case 9:
				moveToBottomEntrance();
				break;
			}
			break;
		case 8:
			switch (zombieQuadrant) {
			case 1:
			case 8:
			case 7:
				moveToPlayer();
				break;
			case 2:
			case 3:
				tld = true;
				moveToTopLeft();
				break;
			case 4:
				moveToBottomRight();
				break;
			case 5:
			case 6:
				moveToBottomLeft();
				break;
			case 9:
				moveToLeftEntrance();
				break;
			}
			break;
		case 9:
			switch (zombieQuadrant) {
			case 1:
			case 8:
			case 7:
				moveToLeftEntrance();
				break;
			case 2:
			case 3:
				tld = true;
				moveToTopLeft();
				break;
			case 4:
				moveToBottomRight();
				break;
			case 5:
			case 6:
				moveToBottomEntrance();
				break;
			case 9:
				moveToPlayer();
				break;
			}
		}
	}

	/** The method for where it resets the directions and such. */
	public void reset() {
		attack = false;
		tr = false;
		tl = false;
		tlu = false;
		tld = false;
		br = false;
		bl = false;
		el = false;
		eb = false;
	}

	/**
	 * The method for where the zombies moves to the player according to the
	 * coordinates.
	 */
	public void moveToPlayer() {
		xCoordinate = px;
		yCoordinate = py;
		reset();
		attack = true;
	}

	/**
	 * The method for where the zombies move to the top left and then resets.
	 * This coordinate is to an imagenary point that allows the zombies to avoid
	 * collision with the walls.
	 */
	public void moveToTopLeft() {
		xCoordinate = ax;
		yCoordinate = ay - 4;
		reset();
		tl = true;
	}

	/**
	 * The method for where the zombies move to the bottom left and then resets.
	 * This coordinate is to an imagenary point that allows the zombies to avoid
	 * collision with the walls.
	 */
	public void moveToBottomLeft() {
		xCoordinate = ax;
		yCoordinate = by;
		reset();
		bl = true;
	}

	/**
	 * The method for where the zombies move to the top right and then resets.
	 * This coordinate is to an imagenary point that allows the zombies to avoid
	 * collision with the walls.
	 */
	public void moveToTopRight() {
		xCoordinate = bx;
		yCoordinate = ay - 4;
		reset();
		tr = true;
	}

	/**
	 * The method for where the zombies move to the bottom right and then
	 * resets. This coordinate is to an imagenary point that allows the zombies
	 * to avoid collision with the walls.
	 */
	public void moveToBottomRight() {
		xCoordinate = bx;
		yCoordinate = by;
		reset();
		br = true;
	}

	/**
	 * The method for where the zombies move to the left entrance and then
	 * resets. This coordinate is to an imagenary point that allows the zombies
	 * to avoid collision with the walls.
	 */
	public void moveToLeftEntrance() {
		xCoordinate = ax;
		yCoordinate = (int) (s.getHeight() / 2 - getHeight() / 2) + 1;
		reset();
		el = true;
	}

	/**
	 * The method for where the zombies move to the bottom entrance and then
	 * resets. This coordinate is to an imagenary point that allows the zombies
	 * to avoid collision with the walls.
	 */
	public void moveToBottomEntrance() {
		xCoordinate = (int) (s.getWidth() / 2 - getWidth() / 2 + 1);
		yCoordinate = by;
		reset();
		eb = true;
	}

	/**
	 * The method for where it controls the actual movement for the zombies
	 * (speed).
	 */
	public void movement() {
		directionDecision();
		dx = getX() - (40 - getWidth()) / 2;
		dy = getY() - (40 - getHeight()) / 2;

		if (dx - xCoordinate > 0) {
			xSpeed = speed * -1;
		} else if (dx - xCoordinate == 0) {
			xSpeed = 0;
		} else if (dx - xCoordinate < 0) {
			xSpeed = Math.abs(speed);
		}

		if (dy - yCoordinate > 0) {
			ySpeed = speed * -1;
		} else if (dy - yCoordinate == 0) {
			ySpeed = 0;
		} else if (dy - yCoordinate < 0) {
			ySpeed = Math.abs(speed);
		}

		if (!verticalCollision) {
			setY(getY() + ySpeed);
		} else if (verticalCollision) {
			setY(getY() - ySpeed);
		}
		if (!horizontalCollision) {
			setX(getX() + xSpeed);
		} else if (horizontalCollision) {
			setX(getX() - xSpeed);
		}
		verticalCollision = false;
		horizontalCollision = false;

		// Adjustments
		if (dx - xCoordinate <= 1 && dx - xCoordinate >= -1
				&& dy - yCoordinate <= 1 && dy - yCoordinate >= -1 && !attack) {
			adjustments();
		}
	}

	/**
	 * The method for where it controls the adjustments when certain corner
	 * areas are hit by zombies.
	 */
	public void adjustments() {
		if (tr || eb || tl && tlu) {
			setY(getY() - 3);
		}
		if (tl) {
			setY(getY() + 4);
			setX(getX() - 4);
		}
		if (bl) {
			setX(getX() - 2);
		}
		if (el) {
			setX(getX() + 3);
		}
	}

	/**
	 * The method for where it detects if the zombies are collided with the
	 * vertical walls.
	 * 
	 * @return verticalCollision always true
	 */
	public boolean verticalWallCollision() {
		return verticalCollision = true;
	}

	/**
	 * The method for where it detects if the zombies are collided with the
	 * horizontal walls.
	 * 
	 * @return horizontalCollison always true
	 */
	public boolean horizontalWallCollision() {
		return horizontalCollision = true;
	}

	/**
	 * Sets up for the x position of the player and detection by the zombies
	 * 
	 * @param px
	 *            the player x position of the object
	 */
	public int playerX(int px) {
		return this.px = px;
	}

	/**
	 * Sets up for the y position of the player and detection by the zombies
	 * 
	 * @param py
	 *            the player y position of the object
	 */
	public int playerY(int py) {
		return this.py = py;
	}

	/**
	 * Sets up the recoil of the zombies when hit
	 * 
	 * @param recoil the recoil of the bullets
	 */
	public int setRecoil(int recoil) {
		return this.recoil = recoil;
	}

	public int resetRecoil() {
		return this.recoil = 20;
	}

	/**
	 * The method for setting up the recoil going right when the zombies are
	 * hit.
	 */
	public void rightRecoil() {
		setX(getX() + recoil);
	}

	/**
	 * The method for setting up the recoil going left when the zombies are hit.
	 */
	public void leftRecoil() {
		setX(getX() - recoil);
	}

	/**
	 * The method for setting up the recoil going downwards when the zombies are
	 * hit.
	 */
	public void downRecoil() {
		setY(getY() + recoil);
	}

	/**
	 * The method for setting up the recoil going upwards when the zombies are
	 * hit.
	 */
	public void upRecoil() {
		setY(getY() - recoil);
	}

	/**
	 * Sets up the movement speed of the zombies
	 * 
	 * @param speed
	 *            the speed of the zombies
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
