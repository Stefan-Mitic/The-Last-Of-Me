package game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The class is for the appearance of the borders.
 * 
 * @author Stefan Mitic, Alex Chan, Sowon Ham
 */

public class Borders extends GameObject {

	@Override
	public void act() {

	}

	@Override
	/** The method for where it paints the actual rectangles and the appearance that
	 *  were included as borders in the game. */
	public void paint(Graphics g) {
		Rectangle r = getBounds();
		g.setColor(c);
		g.fillRect(0, 0, (int) r.getWidth(), (int) r.getHeight());
	}
}
