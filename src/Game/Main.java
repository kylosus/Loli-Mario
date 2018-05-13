package Game;

import Builders.Background;
import Builders.Character;
import Builders.Core;
import Builders.Foreground;
import Characters.*;
import Menu.StartMenu;
import Objects.*;
import acm.graphics.GImage;
import acm.graphics.GObject;
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
	public static StartMenu StartMenu;
	public static boolean isInMenu = true;
	public static Background background;
	public static Foreground foreground;
	private static double WIDTH;
	private static double HEIGHT;
	private static double CHARACTER_SPEED;
	private static double BACKGROUND_SPEED;
	private static double FOREGROUND_SPEED;
	private static final double SPEED_MULTIPLIER = 1.5;
	private final static double PAUSE_TIME_FINAL = 10;
	public static double PAUSE_TIME = 1;
	private static boolean isJumping = false;
	public static boolean RIGHT_KEY_PRESSED = false;
	public static boolean LEFT_KEY_PRESSED = false;
	public static boolean UP_KEY_PRESSED = false;
	public static boolean canJump = true;
	private static boolean SPACE_KEY_PRESSED = false;
	private static boolean IS_FALLING = false;
	private static int JUMP_AIR_TIME = 250;
	private static final int JUMP_AIR_TIME_FINAL = 250;
	private static GPoint upIntersectionPoint = new GPoint();
	private static GPoint downIntersectionPoint = new GPoint();
	private static GPoint rightIntersectionPoint = new GPoint();
	private static GPoint leftIntersectionPoint = new GPoint();
	private static boolean isUpCollision = false;
	public static boolean hasWeaponCrashed = false;
	public static boolean canShoot = true;
	public static GPoint Weapon_Crash_Point;


	public void run() {
		WIDTH = getWidth();
		HEIGHT = getHeight();


		character = new Character();
		character = new Character(Madoka.init(character));
		setCharacters();
		iterator = Characters.iterator();

		foreground = new Foreground(getWidth(), getHeight());
		background = new Background(WIDTH, HEIGHT);

		StartMenu = new StartMenu(getWidth(), getHeight());
		add(StartMenu);

		while (isInMenu) {
			System.out.println("In in Menu");
		}
		add(background);
		add(foreground);
		add(character);
		removeMenu();

		CHARACTER_SPEED = character.Speed;
		BACKGROUND_SPEED = character.Speed / 5;
		FOREGROUND_SPEED = character.Speed / 2;

		setBackground(new Color(84, 208, 249));

//		Sound.playBackground();
		Goomba goomba = (Goomba) (new Goomba().init(foreground));
//		goomba.init(foreground);
		Goomba goomba1 = (Goomba) (new Goomba().init(foreground));
//		goomba1.init(foreground);
		Goomba goomba2 = (Goomba) (new Goomba().init(foreground));
//		goomba2.init(foreground);
		add(goomba, character.getX() + 500, Foreground.REFERENCE_POINT - goomba.getHeight());
		add(goomba1, character.getX() + 600, Foreground.REFERENCE_POINT - goomba.getHeight());
		add(goomba2, character.getX() + 700, Foreground.REFERENCE_POINT - goomba.getHeight());
//		add(goomba, 683, Foreground.REFERENCE_POINT - goomba.getHeight());

//		Objects.FoeBuilder foe = new Objects.FoeBuilder(foreground);

//		add(foe, 500, 100);
//		add(new Goomba().init(foreground), 500, 0);


//		FoeBuilder foe = new FoeBuilder(foreground);
//		add(foe);


		QuestionBlock shroom = foreground.getRandomQuestionBlock();
		shroom.convertToShroom();
		foreground.add(shroom);
		System.out.println(shroom.getX());

		character.setLocation(0, character.getY());
		while (!isGameOver()) {


			if (getElementAt(downIntersectionPoint) instanceof Goomba) {
				if (!((Goomba) getElementAt(downIntersectionPoint)).die()) {
					if (((Goomba) getElementAt(downIntersectionPoint)).isInterractible) {
						break;
					}
				} else {
					JUMP_AIR_TIME = 70;
					UP_KEY_PRESSED = true;
					IS_FALLING = false;
					jump();
				}
			}

			if (isRightCollisionFoe(character)) {
				break;
			}

			if (isLeftCollisionFoe(character)) {
				break;
			}

//			if (goomba.isInterractible && character.getBounds().intersects(goomba.getBounds())) {
//				if (!isOnGround()) {
//					goomba.die();
//					JUMP_AIR_TIME = 70;
//					UP_KEY_PRESSED = true;
//					IS_FALLING = false;
//					jump();
//				} else {
//					break;
//				}
//			}


//			if (foe.contains(character.getX() + character.getWidth() / 2, character.getY() + character.getHeight())) {
//				if (!isOnGround()) {
//					if (foe.getObjectAt(foe.getLocalPoint(character.getX() + character.getWidth() / 2, character.getY() + character.getHeight())).isInterractible) {
//						foe.getObjectAt(foe.getLocalPoint(character.getX() + character.getWidth() / 2, character.getY() + character.getHeight())).die();
//
//						JUMP_AIR_TIME = 70;
//						UP_KEY_PRESSED = true;
//						IS_FALLING = false;
//						jump();
//					}
//				} else {
//					break;
//				}
//			}

			if (!isJumping && !isOnGround()) {
				character.move(0, CHARACTER_SPEED / 2);
			}

			if (RIGHT_KEY_PRESSED && !isRightCollision(character, foreground)) {
				if (isCamera()) {
					moveRight();
//					foe.move(-FOREGROUND_SPEED, 0);
					goomba.move(-FOREGROUND_SPEED, 0);
					goomba1.move(-FOREGROUND_SPEED, 0);
					goomba2.move(-FOREGROUND_SPEED, 0);
					character.weapon.forward.move(FOREGROUND_SPEED, 0);
				} else {
					character.move(FOREGROUND_SPEED, 0);
				}
			}

			if (LEFT_KEY_PRESSED && !isLeftCollision(character, foreground)) {
				character.move(-FOREGROUND_SPEED, 0);
//				moveLeft();
//				goomba.move(FOREGROUND_SPEED, 0);
				character.weapon.forward.move(-FOREGROUND_SPEED, 0);
			}

			if (SPACE_KEY_PRESSED && canShoot && !isRightCollision(character, foreground) && character.getHeight() > 60) {
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

			if (character.getBounds().intersects(shroom.object.getBounds())) {
				shroom.object.runner.kill();
				shroom.object.die();
			}


			// What have I done
			if (isUpCollision && !isOnGround()) {
				if (shroom.contains(upIntersectionPoint) && shroom.isAlive) {
					add(shroom.object, shroom.getLocation());
					shroom.moveObject();
					new Thread(shroom.object.runner = new Runner(shroom.object, foreground)).start();
					System.out.println("Yes");
				}
				if (foreground.contains(upIntersectionPoint)) {
					if (foreground.getElementAt(foreground.getLocalPoint(upIntersectionPoint)) instanceof QuestionBlock) {
						QuestionBlock block = (QuestionBlock) foreground.getElementAt(foreground.getLocalPoint(upIntersectionPoint));
						System.out.println("It is a question block");
						if (block.isAlive) {
							block.moveObject();
							new Thread(new MoveBlock(block)).start();
						}
					} else {
						new Thread(new MoveBlock(foreground.getElementAt(foreground.getLocalPoint(upIntersectionPoint)))).start();
					}
					isUpCollision = false;
				}
			}

			if ((IS_FALLING && !isOnGround())) {
//				if (getElementAt(character.getX() + character.getWidth() / 2, character.getY() + character.getHeight()) instanceof Foe) {
//					JUMP_AIR_TIME = 70;
//					UP_KEY_PRESSED = true;
//					IS_FALLING = false;
//					jump();
//				} else {
				fallDown();
//			}
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
					if (canJump) {
						canJump = false;
						if (isOnGround()) {
							character.jumpForward();
							UP_KEY_PRESSED = true;
							IS_FALLING = false;
						} else {
							break;
						}
					}
					break;
				case KeyEvent.VK_SPACE:
					SPACE_KEY_PRESSED = true;
					break;

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
					break;
				case KeyEvent.VK_UP:
					canJump = true;
					break;
			}
		}
	}

	private boolean isOnGround() {
		upIntersectionPoint.setLocation(character.getX() + character.getWidth() / 2, character.getY());
		downIntersectionPoint.setLocation(character.getX() + character.getWidth() / 2, character.getY() + character.getHeight());
		if (foreground.contains(downIntersectionPoint)) {
			return true;
		} else {
//			JUMP_AIR_TIME = JUMP_AIR_TIME_FINAL;
//			IS_FALLING = true;
//			System.out.println("It is on air");
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

	private boolean isRightCollision(GObject main, GObject collider) {
		return (collider.contains(main.getX() + main.getWidth(), main.getY() + main.getHeight() / 2) ||
				collider.contains(main.getX() + main.getWidth(), main.getY() + main.getHeight() - 10));
	}

	private boolean isRightCollisionFoe(GObject main) {
		return ((getElementAt(main.getX() + main.getWidth(), main.getY() + main.getHeight() / 2)) instanceof Foe) && (((Foe) getElementAt(main.getX() + main.getWidth(), main.getY() + main.getHeight() / 2)).isInterractible);
	}

	private void moveRight() {
		background.move(-BACKGROUND_SPEED, 0);
		foreground.move(-FOREGROUND_SPEED, 0);
	}

	private boolean isLeftCollision(GObject main, GObject collider) {
		return (collider.contains(main.getX(), main.getY() + main.getHeight() / 2) ||
				collider.contains(main.getX(), main.getY() + main.getHeight() - 10));
	}

	private boolean isLeftCollisionFoe(GObject main) {
		return (getElementAt(main.getX(), main.getY() + main.getHeight() / 2) instanceof Foe) && (((Foe) getElementAt(main.getX(), main.getY() + main.getHeight() / 2)).isInterractible);
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
				JUMP_AIR_TIME = JUMP_AIR_TIME_FINAL;
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
			JUMP_AIR_TIME = JUMP_AIR_TIME_FINAL;
			IS_FALLING = false;
			isJumping = false;
		} else {
			character.move(0, CHARACTER_SPEED / 2);
			IS_FALLING = true;
		}
		if (JUMP_AIR_TIME != 0) {
			JUMP_AIR_TIME = JUMP_AIR_TIME_FINAL;
		} else {
			JUMP_AIR_TIME = JUMP_AIR_TIME_FINAL;
			IS_FALLING = false;
			PAUSE_TIME = PAUSE_TIME_FINAL;
		}
	}

	private void die() {
		for (int i = 0; i < JUMP_AIR_TIME_FINAL / 5; i++) {
			character.move(0, -CHARACTER_SPEED * 2);
			pause(PAUSE_TIME * 2);
		}

		for (int i = 0; i < JUMP_AIR_TIME_FINAL / 5 + 3; i++) {
			character.move(0, CHARACTER_SPEED * 2);
			pause(PAUSE_TIME * 2);
		}
	}

	private boolean isUpCollision() {
//		if (foreground.contains(character.getX(), character.getY())) {
//			isUpCollision = true;
//			upIntersectionPoint.setLocation(character.getX() + character.getWidth() / 2, character.getY());
//			return true;
//		}
//
//		if (foreground.contains(character.getX() + character.getWidth(), character.getY())) {
//			isUpCollision = true;
//			upIntersectionPoint.setLocation(character.getX() + character.getWidth() / 2, character.getY());
//			return true;
//		}

		if (foreground.contains(character.getX() + character.getWidth() / 2, character.getY())) {
			isUpCollision = true;
//			upIntersectionPoint.setLocation(character.getX() + character.getWidth() / 2, character.getY());
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
			System.out.println("Character changed to " + character.name);
		} else {
			iterator = Characters.iterator();
			changeCharacter();
		}
	}

	private boolean isCamera() {
		return character.getX() + character.getWidth() > getWidth() / 2;
	}

	public void removeMenu() {
		remove(StartMenu);
	}

	public static void main(String[] args) {
		new Main().start();
	}
}