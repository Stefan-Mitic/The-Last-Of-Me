package game;

import java.awt.Color;

/**
 * The main class for where it controls the functionality of the zombie types
 * within the game, controls the health, speed.
 * 
 * @author Stefan Mitic, Alex Chan, Sowon Ham
 */

public class ZombieType extends ZombieMovement {
	private int health;
	private boolean died = false;

	/**
	 * The method for where it allows the functionality of the zombie spawning
	 * through switch statements; different types.
	 */
	public void zombieSpawning() {
		setSize(35, 35);
		int zombieType = (int) (Math.random() * 4);
		switch (zombieType) {
		case 0:
			setColor(Color.RED);
			health = 2;
			setSpeed(2);
			break;
		case 1:
			setColor(Color.BLUE);
			health = 5;
			setSpeed(2);
			break;
		case 2:
			setColor(Color.GREEN);
			health = 20;
			setSpeed(1);
			break;
		case 3:
			setColor(Color.YELLOW);
			health = 30;
			setSpeed(1);
			break;
		}
		setup();
	}

	/**
	 * The method for where it detects the health decreasing when the bullet
	 * collides with zombies.
	 */
	public void healthDecrease() {
		health--;
	}

	/**
	 * The method for where it detects the zombie's health if it is smaller or
	 * equal to zero.
	 * 
	 * @return a boolean type
	 */
	public boolean healthDetection() {
		// It is for smaller or equal to zero, just in case if the zombie is
		// still alive due to lag.
		if (health <= 0 && !died) {
			setX(-300);
			setY(-300);
			setSpeed(0);
			died = true;
			return true;
		}
		return false;
	}
}
