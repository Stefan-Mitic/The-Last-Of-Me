package menu;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * The class that contains the instructions of the game it certainly and clearly
 * describes how the game works and how to play it.
 * 
 * @author Stefan Mitic, Alex Chan, Sowon Ham
 */
public class Instructions extends Popup {
	private JLabel title = new JLabel("Instructions", SwingConstants.CENTER);
	private JLabel info = new JLabel();
	private JButton back = new JButton("Back");

	/**
	 * The default constructor for the instructions menu.
	 */
	public Instructions() {
		title.setBounds(getWidth() / 2 - buttonWidth() / 2, 20, buttonWidth(),
				40);
		title.setFont(getFont());
		add(title);

		info.setBounds(20, 80, getWidth() - 40, 200);
		info.setFont(new Font("Arial", Font.PLAIN, 18));
		info.setText("<html>Use arrow keys to move around. Space to shoot<br>"
				+ "(in the direction you are facing). "
				+ "You have 3 lives. There are four types of colored squares, "
				+ "each having different properties (list properties here)."
				+ "When a colored square catches up to you, the virus will "
				+ "immediately infect and take over. A number of colored "
				+ "squares will spawn per round. The goal is to survive as long "
				+ "as possible. <br>Good luck!</html>");
		add(info);

		back.setBounds(getWidth() / 2 - buttonWidth() / 2, getHeight()
				- buttonHeight() * 2, buttonWidth(), buttonHeight());
		back.setBackground(getButtonColor());
		back.setForeground(getFontColor());
		back.setFont(getFont());
		back.addActionListener(this);
		add(back);
	}

	@Override
	/** The method for where it detects when certain buttons are pressed in the JFrame. */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			setVisible(false);
		}
	}
}
