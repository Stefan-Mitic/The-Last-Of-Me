package game;

import java.awt.Color;

/**
 * The main class for controlling the functionality of the bullets such as
 * speed, color, and size. Accessed by the Main class through an array.
 * 
 * @author Stefan Mitic, Alex Chan, Sowon Ham
 */

public class Bullets extends GameObject {
	private int bulletSpeed = 10;
	private boolean moveUp = false;
	private boolean moveDown = false;
	private boolean moveLeft = false;
	private boolean moveRight = false;

	public void act() {
		start();
		direction();
	}

	/**
	 * THe method for where when the game starts it automatically sets the size
	 * and color.
	 */
	public void start() {
		setSize(5, 5);
		setColor(Color.BLACK);
	}

	/** The method for where it controls the direction of the bullets. */
	public void direction() {
		if (moveUp) {
			setY(getY() - bulletSpeed);
		}
		if (moveDown) {
			setY(getY() + bulletSpeed);
		}
		if (moveRight) {
			setX(getX() + bulletSpeed);
		}
		if (moveLeft) {
			setX(getX() - bulletSpeed);
		}
	}

	/**
	 * @return moveUp the movement of the bullets to the up
	 */
	public boolean isMoveUp() {
		return moveUp;
	}

	/**
	 * Sets the move up position of this object
	 * 
	 * Setting the move up position will not affect any other direction
	 * 
	 * @param moveUp
	 *            the move up for the bullets
	 */
	public void setMoveUp(boolean moveUp) {
		this.moveUp = moveUp;
	}

	/**
	 * @return moveDown the movement of the bullets to the down
	 */
	public boolean isMoveDown() {
		return moveDown;
	}

	/**
	 * Sets the move up position of this object
	 * 
	 * Setting the move up position will not affect any other direction
	 * 
	 * @param moveDown
	 *            the move down for the bullets
	 */
	public void setMoveDown(boolean moveDown) {
		this.moveDown = moveDown;
	}

	/**
	 * @return moveLeft the movement of the bullets to the left
	 */
	public boolean isMoveLeft() {
		return moveLeft;
	}

	/**
	 * Sets the move up position of this object
	 * 
	 * Setting the move up position will not affect any other direction
	 * 
	 * @param moveLeft
	 *            the move left for the bullets
	 */
	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	/**
	 * @return moveRight the movement of the bullets to the right
	 */
	public boolean isMoveRight() {
		return moveRight;
	}

	/**
	 * Sets the move right position of this object
	 * 
	 * Setting the move right position will not affect any other direction
	 * 
	 * @param moveRight
	 *            the move right for the bullets
	 */
	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
}
