package menu;

import game.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This is the class for the menu where when the game starts it displays the
 * menu rather than playing the game right away, it also includes multiple
 * options to access the instructions, scoreboard, and backstory classes.
 * 
 * @author Stefan Mitic, Alex Chan, Sowon Ham
 */
public class Menu extends JFrame implements ActionListener {
	private Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenWidth = (int) s.getWidth();
	private int screenHeight = (int) s.getHeight();
	private final int BUTTON_WIDTH = 300, BUTTON_HEIGHT = 75;
	private JButton start = new JButton("Start");
	private JButton backstory = new JButton("Backstory");
	private JButton instructions = new JButton("Instructions");
	private JButton scoreBoard = new JButton("Scoreboard");
	private JButton exit = new JButton("Exit");
	private Color buttonColor = Color.RED;
	private Color fontColor = Color.BLACK;
	private Font font = new Font("Arial", Font.PLAIN, 40);
	private static Sound menuSound = new Sound("data/menu");
	private boolean playSound = true;

	/**
	 * The default constructor for the Menu.
	 */
	public Menu() {
		// Playing the music when the game loads with the menu.
		if (playSound) {
			menuSound.play();
			menuSound.loop();
			playSound = false;
		}

		setSize(screenWidth, screenHeight);
		setUndecorated(true);
		try {
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(
					"src/data/whiteBackground.png")))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon img = new ImageIcon("src/data/title.png");
		JLabel title = new JLabel(img);
		title.setBounds(screenWidth / 100, screenHeight / 2 - 500, 800, 800);

		setVisible(true);
		add(title);

		// The setup of the start button on the menu.
		start.setBounds(screenWidth / 2 + BUTTON_WIDTH, screenHeight / 2
				- BUTTON_HEIGHT * 4, BUTTON_WIDTH, BUTTON_HEIGHT);
		start.setBackground(buttonColor);
		start.setForeground(fontColor);
		start.setFont(font);
		start.addActionListener(this);
		add(start);

		// The setup of the backstory button on the menu.
		backstory.setBounds(screenWidth / 2 + BUTTON_WIDTH, screenHeight / 2
				- BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
		backstory.setBackground(buttonColor);
		backstory.setForeground(fontColor);
		backstory.setFont(font);
		backstory.addActionListener(this);
		add(backstory);

		// The setup of the instructions button on the menu.
		instructions.setBounds(screenWidth / 2 + BUTTON_WIDTH,
				screenHeight / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
		instructions.setBackground(buttonColor);
		instructions.setForeground(fontColor);
		instructions.setFont(font);
		instructions.addActionListener(this);
		add(instructions);

		// The setup of the scoreboard button on the menu.
		scoreBoard.setBounds(screenWidth / 2 + BUTTON_WIDTH, screenHeight / 2
				+ BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
		scoreBoard.setBackground(buttonColor);
		scoreBoard.setForeground(fontColor);
		scoreBoard.setFont(font);
		scoreBoard.addActionListener(this);
		add(scoreBoard);

		// The setup of the exit button on the menu.
		exit.setBounds(screenWidth / 2 + BUTTON_WIDTH, screenHeight / 2
				+ BUTTON_HEIGHT * 4, BUTTON_WIDTH, BUTTON_HEIGHT);
		exit.setBackground(buttonColor);
		exit.setForeground(fontColor);
		exit.setFont(font);
		exit.addActionListener(this);
		add(exit);
	}

	/**
	 * The main constructor to run the game. 
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {
		new Menu();
	}

	/**
	 * The function for where when a specific button is clicked it opens a
	 * certain window.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			new Main();
			setVisible(false);
		} else if (e.getSource() == instructions) {
			new Instructions();
		} else if (e.getSource() == backstory) {
			new BackStory();
		} else if (e.getSource() == scoreBoard) {
			new Scoreboard();
		} else if (e.getSource() == exit) {
			System.exit(0);
		}
	}

	/** The sound playing method. */
	public void goPlaySound() {
		playSound = true;
	}

}
