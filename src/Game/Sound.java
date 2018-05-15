package Game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
	private static Clip effect;
	private static Clip background;
	private static Clip bgm;
	private static final File DDLC_SOUNDTRACK = new File("Sounds/DDLC_BACKGROUND.wav");
	private static final File BGM = new File("Sounds/bgm0.wav");
	private static final File HOVER = new File("Sounds/hover.wav");
	private static final File SELECT = new File("Sounds/select.wav");
	private static final File JUMP = new File("Sounds/jump.wav");
	private static final File Special = new File("Sounds/special.wav");

	public static boolean initiate() {
		try {
			bgm = AudioSystem.getClip();
			bgm.open(AudioSystem.getAudioInputStream(BGM));
			effect = AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(HOVER));
			return true;
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void playBackground() {
		try {
			background = AudioSystem.getClip();
			background.open(AudioSystem.getAudioInputStream(DDLC_SOUNDTRACK));
			background.start();
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
		}
		background.start();
	}

	public static void playBGM() {
		bgm.start();
	}

	public static void stopBackground() {
		background.stop();
	}

	public static void playSpecial() {
		try {
			effect = AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(Special));
			effect.start();
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public static void playHover() {
		try {
			effect = AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(HOVER));
			effect.start();
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public static void playSelect() {
		try {
			effect = AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(SELECT));
			effect.start();
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public static void playJump() {
		try {
			effect = AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(HOVER));
			effect.start();
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}