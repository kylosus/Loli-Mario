package Game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Sound {
	private static Clip effect;
	private static Clip background;
	private static Clip bgm;
	private static ArrayList<File> Soundtracks = new ArrayList<>();
	private static Iterator<File> iterator;
	private static final File DDLC_SOUNDTRACK = new File("Sounds/DDLC_BACKGROUND.wav");
	private static final File BGM = new File("Sounds/bgm0.wav");
	private static final File HOVER = new File("Sounds/hover.wav");
	private static final File SELECT = new File("Sounds/select.wav");
	private static final File JUMP = new File("Sounds/jump.wav");
	private static final File COIN = new File("Sounds/coin.wav");
	private static final File BUMP = new File("Sounds/bump.wav");
	private static final File STOMP = new File("Sounds/stomp.wav");
	private static final File SHROOM = new File("Sounds/shroom.wav");
	private static final File LEVELUP = new File("Sounds/levelup.wav");
	private static final File DEATH = new File("Sounds/death.wav");
	private static final File Special = new File("Sounds/special.wav");

	static void initiate() {
		try {
			bgm = AudioSystem.getClip();
			bgm.open(AudioSystem.getAudioInputStream(BGM));
			effect = AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(HOVER));
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
		}
		Soundtracks.add(new File("Sounds/bgm0.wav"));
		Soundtracks.add(new File("Sounds/bgm1.wav"));
		Soundtracks.add(new File("Sounds/bgm2.wav"));
		iterator = Soundtracks.iterator();
	}

	static void playBackground() {
		try {
			background = AudioSystem.getClip();
			background.open(AudioSystem.getAudioInputStream(DDLC_SOUNDTRACK));
			background.start();
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
		}
		background.start();
	}

	static void playBGM() {
		bgm.start();
	}

	static void stopBackground() {
		background.stop();
	}

	static void stopBGM() {
		bgm.stop();
	}

	@SuppressWarnings("unused")
	public static void stopEffect() {
		effect.stop();
	}

	static void playSpecial() {
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

	static void playJump() {
		try {
			effect = AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(JUMP));
			effect.start();
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public static void playCoin() {
		try {
			effect = AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(COIN));
			effect.start();
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	static void playBump() {
		try {
			effect = AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(BUMP));
			effect.start();
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public static void playStomp() {
		try {
			effect = AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(STOMP));
			effect.start();
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
		}
	}


	static void playShroom() {
		try {
			effect = AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(SHROOM));
			effect.start();
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	static void playLevelup() {
		try {
			effect = AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(LEVELUP));
			effect.start();
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	static void playDeath() {
		try {
			effect = AudioSystem.getClip();
			effect.open(AudioSystem.getAudioInputStream(DEATH));
			effect.start();
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public static void changeBGM() {
		stopBackground();
		stopBGM();
		try {
			bgm = AudioSystem.getClip();
			if (iterator.hasNext()) {
				bgm.open(AudioSystem.getAudioInputStream(iterator.next()));
			} else {
				iterator = Soundtracks.iterator();
				changeBGM();
			}
			bgm.start();
		} catch (javax.sound.sampled.UnsupportedAudioFileException | java.io.IOException | javax.sound.sampled.LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}