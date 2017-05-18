package menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The class controls the main functionality of the game after the game is
 * finished. It asks the user for a name to input it into the scoreboard.
 * 
 * @author Stefan Mitic, Alex Chan, Sowon Ham
 */

public class GameOver extends Popup {
	private JLabel title = new JLabel("Gameover", SwingConstants.CENTER);
	private JLabel info = new JLabel();
	private JButton scoreboard = new JButton("Enter");
	private JTextField nameField = new JTextField();
	private String names[] = new String[5];
	private int highScore[] = new int[5];
	private String name = "", text;
	private int round = 0, place;

	/**
	 * The default constructor for the game over menu.
	 */
	public GameOver() {
		setVisible(true);
		title.setBounds(getWidth() / 2 - buttonWidth() / 2, 20, buttonWidth(),
				50);
		title.setFont(getFont());
		add(title);

		info.setBounds(getWidth() / 2 - buttonWidth() / 2, 80, buttonWidth(),
				40);
		info.setFont(new Font("Arial", Font.PLAIN, 30));
		info.setText("<html>Enter Your Name:</html>");
		add(info);

		nameField.setBounds(getWidth() / 2 - buttonWidth() / 2, 120,
				buttonWidth(), 40);
		nameField.setFont(new Font("Arial", Font.PLAIN, 30));
		add(nameField);

		scoreboard.setBounds(getWidth() / 2 - buttonWidth() / 2, getHeight()
				- buttonHeight() * 2, buttonWidth(), buttonHeight());
		scoreboard.setBackground(getButtonColor());
		scoreboard.setForeground(getFontColor());
		scoreboard.setFont(getFont());
		scoreboard.addActionListener(this);
		add(scoreboard);
	}

	/**
	 * Sets the round of this object
	 * 
	 * Setting the rounds as the variable r and controls the rounds.
	 * 
	 * @param r
	 *            the variable for where it just equals to the rounds in the
	 *            game.
	 */
	public void setRound(int r) {
		round = r;
	}

	@Override
	/** The actions performed for when certain specific buttons are clicked. */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == scoreboard) {
			Scoreboard sc = new Scoreboard();
			name = nameField.getText();
			scoreList();
			sc.setText(text);
			setVisible(false);
		}
	}

	/**
	 * The method for it keeps the scores for records and displaying the highest
	 * scores.
	 */
	public void scoreList() {
		File file = new File("src/data/score");
		Scanner fileIn = null;
		PrintWriter output = null;

		try {
			fileIn = new Scanner(new FileReader("src/data/score"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int counter = 0; fileIn.hasNext(); counter++) {
			names[counter] = fileIn.next();
			highScore[counter] = fileIn.nextInt();
		}

		if (name.length() > 0) {
			try {
				output = new PrintWriter(file);
			} catch (FileNotFoundException ex) {
				System.out.println("Cannot open");
				System.exit(1);
			}

			for (int counter = 0, next = 0; next != 1; counter++) {
				if (round >= highScore[counter]) {
					place = counter;
					for (int counter1 = 4; counter1 > place; counter1--) {
						highScore[counter1] = highScore[counter1 - 1];
					}
					for (int counter1 = 4; counter1 > place; counter1--) {
						names[counter1] = names[counter1 - 1];
					}
					highScore[counter] = round;
					names[counter] = name;
					next = 1;
				}
			}

			for (int counter = 0; counter < 5; counter++) {
				output.format("%10s %9s", names[counter], highScore[counter]
						+ "\n");
			}

			output.close();
		}

		// accessed in the scoreboard class
		text = "<html>" + names[0] + " " + highScore[0] + "<br>" + names[1]
				+ " " + highScore[1] + "<br>" + names[2] + " " + highScore[2]
				+ "<br>" + names[3] + " " + highScore[3] + "<br>" + names[4]
				+ " " + highScore[4] + "<br>" + "</html>";
	}
}
