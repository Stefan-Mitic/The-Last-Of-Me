package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * The class that controls the pop ups for the small JFrames, this includes the
 * Instructions, BackStory, GameOver, and Scoreboard classes.
 * 
 * @author Stefan Mitic, Alex Chan, Sowon Ham
 */

public abstract class Popup extends JFrame implements ActionListener {
	private Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
	private final int BUTTON_WIDTH = 300, BUTTON_HEIGHT = 75;
	private final Color BUTTON_COLOR = Color.RED;
	private final Color FONT_COLOR = Color.BLACK;
	private final Font FONT = new Font("Arial", Font.PLAIN, 40);

	/**
	 * The default constructor for the popups.
	 */
	public Popup() {
		setBackground(Color.BLACK);
		setSize(500, 500);
		setLayout(null);
		setLocation((int) (s.getWidth() / 2 - getWidth() / 2),
				(int) (s.getHeight() / 2 - getHeight() / 2));
		setVisible(true);
	}

	/**
	 * @return BUTTON_WIDTH the width size of the buttons
	 */
	public int buttonWidth() {
		return BUTTON_WIDTH;
	}

	/**
	 * @return BUTTON_HEIGHT the height size of the buttons
	 */
	public int buttonHeight() {
		return BUTTON_HEIGHT;
	}

	/**
	 * @return BUTTON_COLOR the color of the buttons
	 */
	public Color getButtonColor() {
		return BUTTON_COLOR;
	}

	/**
	 * @return FONT_COLOR the color of the font
	 */
	public Color getFontColor() {
		return FONT_COLOR;
	}

	/**
	 * @return FONT the appearance of the font or the actual font type
	 */
	public Font getFont() {
		return FONT;
	}

	public abstract void actionPerformed(ActionEvent e);
}
