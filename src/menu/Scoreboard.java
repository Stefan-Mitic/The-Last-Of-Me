package menu;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * The class that stores the JFrame for the score board. It includes the score
 * itself that is saved in the file score. It accesses the GameOver class and
 * displays what the user had inputed on the GameOver window.
 * 
 * @author Stefan Mitic, Alex Chan, Sowon Ham
 */

public class Scoreboard extends Popup {
	private JLabel title = new JLabel("Scoreboard", SwingConstants.CENTER);
	private JButton back = new JButton("Back");
	private JLabel info = new JLabel();

	/**
	 * The default constructor for the scoreboard JFrame.
	 */
	public Scoreboard() {
		title.setBounds(getWidth() / 2 - buttonWidth() / 2, 20, buttonWidth(),
				50);
		title.setFont(getFont());
		add(title);

		back.setBounds(getWidth() / 2 - buttonWidth() / 2, getHeight()
				- buttonHeight() * 2, buttonWidth(), buttonHeight());
		back.setBackground(getButtonColor());
		back.setForeground(getFontColor());
		back.setFont(getFont());
		back.addActionListener(this);
		add(back);

		info.setBounds(getWidth() / 2 - 50, 80, getWidth() - 40, 200);
		info.setFont(new Font("Arial", Font.PLAIN, 30));
		add(info);
	}

	/**
	 * Sets the text on the score board window
	 * 
	 * Setting up the text on the score board window when the button is clicked
	 * 
	 * @param text
	 *            the setup of the text on the score board window
	 */
	public void setText(String text) {
		info.setText(text);
	}

	@Override
	/** The method for where it detects and performs a specific functionality when a 
	 * certain button is clicked.*/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			setVisible(false);
		}
	}

}
