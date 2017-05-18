package menu;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * The class for where it has the functionality of the button Back story in the
 * main menu and explains the history of the game.
 * 
 * @author Stefan Mitic, Alex Chan, Sowon Ham
 */

public class BackStory extends Popup {
	private JLabel title = new JLabel("Backstory", SwingConstants.CENTER);
	private JLabel info = new JLabel();
	private JButton back = new JButton("Back");

	public BackStory() {
		title.setBounds(getWidth() / 2 - buttonWidth() / 2, 20, buttonWidth(),
				50);
		title.setFont(getFont());
		add(title);

		info.setBounds(20, 80, getWidth() - 40, 200);
		info.setFont(new Font("Arial", Font.PLAIN, 16));
		info.setText("<html>It was another quiet morning in sq. ville, "
				+ "devoid of color as usual. After wasting another morning "
				+ "hanging around the town square, you notice a small commotion "
				+ "on the other side. Curious, you move closer to check it out. "
				+ "In the corner of your eye, you spot something completely out of "
				+ "this world. You hear someone shout, \"Is that... is that a color?!?!\""
				+ " A moment of silence, and then, panic. Squares everywhere running for "
				+ "their lives, as the color slowly begins to spread. A few weeks "
				+ "later, and you are the last of your resistance group. "
				+ "Determined to make your last stand, you prepare for the tough"
				+ " fight ahead...</html>");
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
	/** The method for where it detects a certain button being pressed and actioning upon it. */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			setVisible(false);
		}
	}
}
