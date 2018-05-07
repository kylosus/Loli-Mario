import Builders.Character;
import Characters.*;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Main extends GraphicsProgram {

	public static ArrayList<Character> Characters = new ArrayList<>();
	private static Iterator<Character> iterator = Characters.iterator();
	public static Character character;
	//	public static GImage weapon = new GImage(Madoka.WeaponForward.getImage());
	public static StartMenu StartMenu;
	public static boolean isInMenu = true;
	public static Background background;
	public static Foreground foreground;
	private static double WIDTH;
	private static double HEIGHT;
	private static double CHARACTER_SPEED;
	private static double BACKGROUND_SPEED;
	private static double FOREGROUND_SPEED;
	private final static double PAUSE_TIME_FINAL = 10;
	public static double PAUSE_TIME = 5;
	private static boolean isJumping = false;
	public static boolean RIGHT_KEY_PRESSED = false;
	public static boolean LEFT_KEY_PRESSED = false;
	public static boolean UP_KEY_PRESSED = false;
	private static boolean SPACE_KEY_PRESSED = false;
	private static boolean IS_FALLING = false;
	private static int JUMP_AIR_TIME = 50;
	private static final int AIR_TIME_FINAL = 50;
	private static GPoint intersectionPoint = new GPoint();
	private static boolean isUpCollision = false;
	public static boolean hasWeaponCrashed = false;
	public static boolean canShoot = true;
	public static GPoint Weapon_Crash_Point;


	public void run() {

		WIDTH = getWidth();
		HEIGHT = getHeight();
		foreground = new Foreground(getWidth(), getHeight());
		background = new Background(WIDTH, HEIGHT);

		character = new Character();


		character = new Character(Madoka.init(character));


		setCharacters();
		iterator = Characters.iterator();
//		character = new Character(new Homura());

		StartMenu = new StartMenu(getWidth(), getHeight());
		add(StartMenu);

		while (isInMenu) ;

		add(character, getWidth() / 2, Foreground.REFERENCE_POINT - character.getHeight());
		CHARACTER_SPEED = character.Speed;
		BACKGROUND_SPEED = character.Speed / 5;
		FOREGROUND_SPEED = character.Speed / 2;


		setBackground(new Color(84, 208, 249));
		add(background);
		add(foreground);


		Sound.playBackground();

//		QuestionBlock block = new QuestionBlock();
//		add(block, 200, 200);

		while (!isGameOver()) {
			System.out.println("");

			if (!isJumping && !isOnGround()) {
				character.move(0, CHARACTER_SPEED / 2);
			}

			if (RIGHT_KEY_PRESSED && !isRightCollision()) {
				moveRight();
				character.weapon.forward.move(FOREGROUND_SPEED, 0);
			}

			if (LEFT_KEY_PRESSED && !isLeftCollision()) {
				moveLeft();
				character.weapon.forward.move(-FOREGROUND_SPEED, 0);
			}

			if (SPACE_KEY_PRESSED && canShoot && !isRightCollision() && character.getHeight() > 60) {
				character.setShootingForward();
				add(
						character.weapon.forward,
						character.getX() + character.weapon.StartPoint.getX(),
						character.getY() + character.weapon.StartPoint.getY()
				);
				new Thread(new WeaponShooter(character.weapon.forward)).start();
			}

			if (character.weapon.forward.getX() > character.getX() + 800) {
				remove(character.weapon.forward);
				canShoot = true;
				hasWeaponCrashed = false;
			}

			if (hasWeaponCrashed) {
				add(
						character.weapon.Explosion,
						character.weapon.forward.getX(),
						character.weapon.forward.getY() - character.weapon.Explosion.getHeight() / 2
				);
				foreground.remove((foreground.getElementAt(foreground.getLocalPoint(Weapon_Crash_Point))));
				remove(character.weapon.forward);
				hasWeaponCrashed = false;
			}

			if (UP_KEY_PRESSED) {
				jump();
			}

			if (isUpCollision && !isOnGround()) {
				if (foreground.contains(intersectionPoint)) {
					new Thread(new MoveBlock(foreground.getElementAt(foreground.getLocalPoint(intersectionPoint)))).start();
				}
				isUpCollision = false;
			}

			if ((IS_FALLING && !isOnGround())) {
				fallDown();
			}

			pause(PAUSE_TIME);
		}

		die();
		displayGameLostScreen();
	}

	public void init() {
		addKeyListeners();
		addMouseListeners();
		setSize(1920, 580);
	}

	public void keyPressed(KeyEvent e) {
		if (character != null) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					character.setRunningForward();
					RIGHT_KEY_PRESSED = true;
					break;
				case KeyEvent.VK_LEFT:
					character.setRunningBackward();
					LEFT_KEY_PRESSED = true;
					break;
				case KeyEvent.VK_UP:
					if (isOnGround()) {
						character.jumpForward();
						UP_KEY_PRESSED = true;
						IS_FALLING = false;
					}
					break;
				case KeyEvent.VK_SPACE:
					SPACE_KEY_PRESSED = true;

			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (character != null) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					RIGHT_KEY_PRESSED = false;
					character.setStandStillForward();
					break;
				case KeyEvent.VK_LEFT:
					character.setStandStillBackward();
					LEFT_KEY_PRESSED = false;
					break;
				case KeyEvent.VK_SPACE:
					SPACE_KEY_PRESSED = false;
			}
		}
	}

	private boolean isOnGround() {
		if (foreground.contains(character.getX() + character.getWidth() / 2, character.getY() + character.getHeight())) {
			return true;
		} else {
			JUMP_AIR_TIME = AIR_TIME_FINAL;
			IS_FALLING = true;
			System.out.println("It is on air");
			return false;
		}
	}

	private boolean isGameOver() {
		if (!(character.getY() + character.getHeight() < getHeight())) {
			return true;
		} else {
			return false;
		}
	}

	private void displayGameLostScreen() {
		remove(background);
		remove(character);
		remove(foreground);
		add(
				Background.gameLostScreen,
				(WIDTH - Background.gameLostScreen.getWidth()) / 2,
				(HEIGHT - Background.gameLostScreen.getHeight()) / 2
		);
	}

	private boolean isRightCollision() {
		return (foreground.contains(character.getX() + character.getWidth(), character.getY() + character.getHeight() / 2) ||
				foreground.contains(character.getX() + character.getWidth(), character.getY() + character.getHeight() - 10));
	}

	private void moveRight() {
		background.move(-BACKGROUND_SPEED, 0);
		foreground.move(-FOREGROUND_SPEED, 0);
	}

	private boolean isLeftCollision() {
		return (foreground.contains(character.getX(), character.getY() + character.getHeight() / 2) ||
				foreground.contains(character.getX(), character.getY() + character.getHeight() - 10));
	}

	private void moveLeft() {
		background.move(BACKGROUND_SPEED, 0);
		foreground.move(FOREGROUND_SPEED, 0);
	}

	private void jump() {

		if (!isUpCollision()) {
			isJumping = true;
			character.move(0, -CHARACTER_SPEED / 2);
			if (JUMP_AIR_TIME != 0) {
				JUMP_AIR_TIME = JUMP_AIR_TIME - 1;
			} else {
				JUMP_AIR_TIME = AIR_TIME_FINAL;
				UP_KEY_PRESSED = false;
				IS_FALLING = true;
			}
		} else {
			UP_KEY_PRESSED = false;
			IS_FALLING = true;
		}
	}

	private void fallDown() {
		if (foreground.contains(character.getX(), character.getY() + character.getHeight())) {
			JUMP_AIR_TIME = AIR_TIME_FINAL;
			IS_FALLING = false;
			isJumping = false;
		} else {
			character.move(0, CHARACTER_SPEED / 2);
			IS_FALLING = true;
		}
		if (JUMP_AIR_TIME != 0) {
			JUMP_AIR_TIME = AIR_TIME_FINAL;
		} else {
			JUMP_AIR_TIME = AIR_TIME_FINAL;
			IS_FALLING = false;
			PAUSE_TIME = PAUSE_TIME_FINAL;
		}
	}

	private void die() {
		for (int i = 0; i < AIR_TIME_FINAL / 5; i++) {
			character.move(0, -CHARACTER_SPEED * 2);
			pause(PAUSE_TIME * 2);
		}

		for (int i = 0; i < AIR_TIME_FINAL / 5 + 3; i++) {
			character.move(0, CHARACTER_SPEED * 2);
			pause(PAUSE_TIME * 2);
		}
	}

	private boolean isUpCollision() {
//		if (foreground.contains(character.getX(), character.getY())) {
//			isUpCollision = true;
//			intersectionPoint.setLocation(character.getX() + character.getWidth() / 2, character.getY());
//			return true;
//		}
//
//		if (foreground.contains(character.getX() + character.getWidth(), character.getY())) {
//			isUpCollision = true;
//			intersectionPoint.setLocation(character.getX() + character.getWidth() / 2, character.getY());
//			return true;
//		}

		if (foreground.contains(character.getX() + character.getWidth() / 2, character.getY())) {
			isUpCollision = true;
			intersectionPoint.setLocation(character.getX() + character.getWidth() / 2, character.getY());
			return true;
		} else {
			isUpCollision = false;
			return false;
		}
	}

	private void setCharacters() {
		Characters.add(new Homura());
		Characters.add(Madoka.init(character));
		Characters.add(new Mami());
	}

	public static void changeCharacter() {
		if (iterator.hasNext()) {
			character = new Character(iterator.next());
			System.out.println("Character change to " + character.name);
		} else {
			iterator = Characters.iterator();
			changeCharacter();
		}
	}

	public static void main(String[] args) {
		new Main().start();
	}
}