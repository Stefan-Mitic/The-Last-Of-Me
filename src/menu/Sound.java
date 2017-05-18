package menu;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Handles playing, stoping, and looping of sounds for the game.
 * 
 * @author Tyler Thomas, modified by Stefan Mitic
 * 
 */
public class Sound {
	private Clip clip;

	/**
	 * specify the sound to play (assuming the sound can be played by the audio
	 * system) from a wave File
	 * 
	 * @param fileName a String
	 */
	public Sound(String fileName) {
		fileName = "src/" + fileName + ".wav";
		try {
			File file = new File(fileName);
			if (file.exists()) {
				AudioInputStream sound = AudioSystem.getAudioInputStream(file);
				// load the sound into memory (a Clip)
				clip = AudioSystem.getClip();
				clip.open(sound);
			} else {
				throw new RuntimeException("Sound: file not found: " + fileName);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Malformed URL: " + e);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Unsupported Audio File: " + e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Input/Output Error: " + e);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Sound: Line Unavailable Exception Error: " + e);
		}
	}

	/**
	 * Play the sound clip
	 */
	public void play() {
		clip.setFramePosition(0); // Must always rewind!
		clip.start();
	}

	/**
	 * Loop the sound clip
	 */
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	/**
	 * Stop the sound clip
	 */
	public void stop() {
		clip.stop();
	}

	/**
	 * Resume the sound clip
	 */
	public void resume() {
		clip.start();
	}
}