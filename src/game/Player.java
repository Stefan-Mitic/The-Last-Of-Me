package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * The class for the functionality of the players where it controls how the
 * player is moving where it contains the up, down, left, and right key
 * detection. Also has the boundaries to the players movement.
 * 
 * @author Stefan Mitic, Alex Chan, Sowon Ham
 */

public class Player extends GameObject {

	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private int pHeight = 40, pWidth = 40;
	private boolean up = false, down = false, left = false, right = false;

	@Override
	public void act() {
		boundaries();
	}

	/**
	 * The method for where it starts up the player movements, setups, the size,
	 * and color.
	 */
	public void start() {
		setX((int) d.getWidth() / 2 - pWidth / 2);
		setY((int) d.getHeight() / 2 - pHeight / 2);
		setSize(pWidth, pHeight);
		setColor(Color.BLACK);
	}

	/**
	 * The method for where it sets the boundaries for the players cannot go
	 * through.
	 */
	private void boundaries() {
		if (getX() <= 0) {
			setX(0);
		} else if (getX() >= d.getWidth() - 40) {
			setX((int) d.getWidth() - 40);
		}

		if (getY() <= 0) {
			setY(0);
		} else if (getY() >= d.getHeight() - 40) {
			setY((int) d.getHeight() - 40);
		}
	}

	/**
	 * @return up the movement of the bullets to the up
	 */
	public boolean getUp() {
		return up;
	}

	/**
	 * Sets the move up position of this object
	 * 
	 * Setting the move up position will not affect any other direction
	 * 
	 * @param up the move up for the bullets
	 */
	public void setUp(boolean up) {
		this.up = up;
	}

	/**
	 * @return down the movement of the bullets to the down
	 */
	public boolean getDown() {
		return down;
	}

	/**
	 * Sets the move down position of this object
	 * 
	 * Setting the move down position will not affect any other direction
	 * 
	 * @param down
	 *            the move down for the bullets
	 */
	public void setDown(boolean down) {
		this.down = down;
	}

	/**
	 * @return left the movement of the bullets to the left
	 */
	public boolean getLeft() {
		return left;
	}

	/**
	 * Sets the move left position of this object
	 * 
	 * Setting the move left position will not affect any other direction
	 * 
	 * @param left
	 *            the move left for the bullets
	 */
	public void setLeft(boolean left) {
		this.left = left;
	}

	/**
	 * @return right the movement of the bullets to the right
	 */
	public boolean getRight() {
		return right;
	}

	/**
	 * Sets the move right position of this object
	 * 
	 * Setting the move right position will not affect any other direction
	 * 
	 * @param right
	 *            the move right for the bullets
	 */
	public void setRight(boolean right) {
		this.right = right;
	}
}
