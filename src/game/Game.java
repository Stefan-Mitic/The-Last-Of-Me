package game;

/*
 * This code is protected under the Gnu General Public License (Copyleft), 2005 by
 * IBM and the Computer Science Teachers of America organization. It may be freely
 * modified and redistributed under educational fair use.
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * An abstract Game class.<br>
 * <br>
 * The default controls are for "Player 1" to move left and right with the 'A'
 * and 'D' keys, up and down with the 'W' and 'S' keys, and shoot with the space
 * key.<br>
 * <br>
 * Before the Game begins, the <code>setup</code> method is executed. This will
 * allow the programmer to add any objects to the game and set them up. When the
 * game begins, the <code>act</code> method is executed every millisecond. This
 * will allow the programmer to check for user input and respond to it.
 * 
 * @see GameObject
 */
public abstract class Game extends JFrame {
	// private boolean _isSetup = false;
	// private boolean _initialized = false;
	private ArrayList _ObjectList = new ArrayList();
	private Timer _t;

	/**
	 * <code>true</code> if the Space key is being held down
	 */
	private boolean Space = false;

	/**
	 * <code>true</code> if the 'A' key is being held down
	 */
	private boolean Left = false;

	/**
	 * <code>true</code> if the 'D' key is being held down.
	 */
	private boolean Right = false;

	/**
	 * <code>true</code> if the 'W' key is being held down.
	 */
	private boolean Up = false;

	/**
	 * <code>true</code> if the 'S' key is being held down.
	 */
	private boolean Down = false;

	/**
	 * Returns <code>true</code> if the Space key is being pressed down
	 * 
	 * @return <code>true</code> if the Space key is being pressed down
	 */
	public boolean spaceKeyPressed() {
		return Space;
	}

	/**
	 * Changes the Space pressed according to the desired type. Used to set a
	 * delay to the bullets.
	 * 
	 * @param set is a boolean
	 */
	public void setSpaceKeyPressed(boolean set) {
		Space = set;
	}

	/**
	 * Returns <code>true</code> if the 'W' key is being pressed down
	 * 
	 * @return <code>true</code> if the 'W' key is being pressed down
	 */
	public boolean WKeyPressed() {
		return Up;
	}

	/**
	 * Returns <code>true</code> if the 'A' key is being pressed down
	 * 
	 * @return <code>true</code> if the 'A' key is being pressed down
	 */
	public boolean AKeyPressed() {
		return Left;
	}

	/**
	 * Returns <code>true</code> if the 'S' key is being pressed down
	 * 
	 * @return <code>true</code> if the 'S' key is being pressed down
	 */
	public boolean SKeyPressed() {
		return Down;
	}

	/**
	 * Returns <code>true</code> if the 'D' key is being pressed down
	 * 
	 * @return <code>true</code> if the 'D' key is being pressed down
	 */
	public boolean DKeyPressed() {
		return Right;
	}

	/**
	 * When implemented, this will allow the programmer to initialize the game
	 * before it begins running
	 * 
	 * Adding objects to the game and setting their initial positions should be
	 * done here.
	 * 
	 * @see GameObject
	 */
	public abstract void setup();

	/**
	 * When the game begins, this method will automatically be executed every
	 * millisecond
	 * 
	 * This may be used as a control method for checking user input and
	 * collision between any game objects
	 */
	public abstract void act();

	/**
	 * Sets up the game and any objects.
	 * 
	 * This method should never be called by anything other than a
	 * <code>main</code> method after the frame becomes visible.
	 */
	public void initComponents() {
		setup();
		for (int i = 0; i < _ObjectList.size(); i++) {
			GameObject o = (GameObject) _ObjectList.get(i);
			o.repaint();
		}
		_t.start();
	}

	/**
	 * Adds a game object to the screen
	 * 
	 * Any added objects will have their <code>act</code> method called every
	 * millisecond
	 * 
	 * @param o
	 *            the <code>GameObject</code> to add.
	 * @see GameObject#act()
	 */
	public void add(GameObject o) {
		_ObjectList.add(o);
		getContentPane().add(o);
	}

	/**
	 * Removes a game object from the screen
	 * 
	 * @param o
	 *            the <code>GameObject</code> to remove
	 * @see GameObject
	 */
	public void remove(GameObject o) {
		_ObjectList.remove(o);
		getContentPane().remove(o);
	}

	/**
	 * Sets the millisecond delay between calls to <code>act</code> methods.
	 * 
	 * Increasing the delay will make the game run "slower." The default delay
	 * is 1 millisecond.
	 * 
	 * @param delay
	 *            the number of milliseconds between calls to <code>act</code>
	 * @see Game#act()
	 * @see GameObject#act()
	 */
	public void setDelay(int delay) {
		_t.setDelay(delay);
	}

	/**
	 * The default constructor for the game.
	 */
	public Game() {
		setTitle("The Last of Me");
		setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		try {
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(
					"src/data/tiledBackground.png")))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Add window listener.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		_t = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				act();
				for (int i = 0; i < _ObjectList.size(); i++) {
					GameObject o = (GameObject) _ObjectList.get(i);
					o.act();
				}
			}
		});

		// Add key listener
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				char pressed = Character.toUpperCase(e.getKeyChar());
				switch (pressed) {
				case 'W':
					Up = true;
					break;
				case 'A':
					Left = true;
					break;
				case 'S':
					Down = true;
					break;
				case 'D':
					Right = true;
					break;
				case KeyEvent.VK_SPACE:
					Space = true;
					break;
				}
			}

			public void keyReleased(KeyEvent e) {
				char released = Character.toUpperCase(e.getKeyChar());
				switch (released) {
				case 'W':
					Up = false;
					break;
				case 'A':
					Left = false;
					break;
				case 'S':
					Down = false;
					break;
				case 'D':
					Right = false;
					break;
				case KeyEvent.VK_SPACE:
					Space = false;
					break;
				}
			}
		});
	}

	/**
	 * Starts updates to the game
	 * 
	 * The game should automatically start.
	 * 
	 * @see Game#stopGame()
	 */
	public void startGame() {
		Up = false;
		Down = false;
		Left = false;
		Right = false;
		_t.start();
	}

	/**
	 * Stops updates to the game
	 * 
	 * This can act like a "pause" method
	 * 
	 * @see Game#startGame()
	 */
	public void stopGame() {
		_t.stop();
	}

	/**
	 * Gets the pixel width of the visible playing field
	 * 
	 * @return a width in pixels
	 */
	public int getFieldWidth() {
		return getContentPane().getBounds().width;
	}

	/**
	 * Gets the pixel height of the visible playing field
	 * 
	 * @return a height in pixels
	 */
	public int getFieldHeight() {
		return getContentPane().getBounds().height;
	}

	class _WinDialog extends JDialog {
		JButton ok = new JButton("OK");

		_WinDialog(JFrame owner, String title) {
			super(owner, title);
			Rectangle r = owner.getBounds();
			setSize(200, 100);
			setLocation(r.x + r.width / 2 - 100, r.y + r.height / 2 - 50);
			getContentPane().add(ok);
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					_WinDialog.this.setVisible(false);
				}
			});
		}
	}
}