package Game;

import Builders.Background;
import Builders.Character;
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

	private static ArrayList<Character> Characters = new ArrayList<>();
	private static Iterator<Character> iterator = Characters.iterator();
	public static Character character;
	private static StartMenu StartMenu;
	public static boolean isInMenu = true;
	private static Background background;
	public static Foreground foreground;
	public static Stats stats;
	private static double WIDTH;
	private static double HEIGHT;
	private static double CHARACTER_SPEED;
	private static double BACKGROUND_SPEED;
	private static double FOREGROUND_SPEED;
	private final static double PAUSE_TIME_FINAL = 10;
	private static double PAUSE_TIME = 1;
	private static boolean isJumping = false;
	private static boolean RIGHT_KEY_PRESSED = false;
	private static boolean LEFT_KEY_PRESSED = false;
	private static boolean UP_KEY_PRESSED = false;
	private static boolean SPECIAL_KEY_PRESSED = false;
	private static boolean canJump = true;
	private static boolean SPACE_KEY_PRESSED = false;
	private static boolean IS_FALLING = false;
	private static int JUMP_AIR_TIME = 250;
	private static final int JUMP_AIR_TIME_FINAL = 250;
	private static GPoint upIntersectionPoint = new GPoint();
	private static GPoint downIntersectionPoint = new GPoint();
	private static boolean isUpCollision = false;
	static boolean hasWeaponCrashed = false;
	static boolean canShoot = true;
	static GPoint Weapon_Crash_Point;
	private static QuestionBlock ShroomBlock;


	public void run() {
		WIDTH = getWidth();
		HEIGHT = getHeight();


		character = new Character();
		character = new Character(Madoka.init(character));
		setCharacters();
		iterator = Characters.iterator();

		foreground = new Foreground(getWidth(), getHeight());
		background = new Background(HEIGHT);

		Sound.initiate();
		Sound.playBackground();
		StartMenu = new StartMenu(getWidth(), getHeight());
		add(StartMenu);

		while (isInMenu) {
			System.out.println("In in Menu");
		}

		Sound.stopBackground();
		Sound.playBGM();


		add(background);
		add(foreground);
		add(character);
		removeMenu();

		CHARACTER_SPEED = character.Speed;
		BACKGROUND_SPEED = character.Speed / 5;
		FOREGROUND_SPEED = character.Speed / 2;

		setBackground(new Color(84, 208, 249));


		Goomba goomba0 = (Goomba) (new Goomba().init(foreground));
		Goomba goomba1 = (Goomba) (new Goomba().init(foreground));
		Goomba goomba2 = (Goomba) (new Goomba().init(foreground));
		add(goomba0, character.getX() + 450, Foreground.REFERENCE_POINT - goomba0.getHeight());
		add(goomba1, character.getX() + 500, Foreground.REFERENCE_POINT - goomba0.getHeight());
		add(goomba2, character.getX() + 550, Foreground.REFERENCE_POINT - goomba0.getHeight());


		initiateNewShroom();
		System.out.println(ShroomBlock.getX());

		character.setLocation(0, character.getY());

		stats = new Stats(getWidth(), getHeight());
		add(stats, 0, stats.getHeight());

		int lives = 3;

		for (int i = 0; i < lives; i++) {
			while (!isGameOver()) {

				if (character.getBounds().intersects(goomba0.getBounds()) && goomba0.isInterractible) {
					if (!isOnGround()) {
						goomba0.die();
						smallJump();
					} else {
						break;
					}
				}

				if (character.getBounds().intersects(goomba1.getBounds()) && goomba1.isInterractible) {
					if (!isOnGround()) {
						goomba1.die();
						smallJump();
					} else {
						break;
					}
				}

				if (character.getBounds().intersects(goomba2.getBounds()) && goomba2.isInterractible) {
					if (!isOnGround()) {
						goomba2.die();
						smallJump();
					} else {
						break;
					}
				}

				if (!isJumping && !isOnGround()) {
					character.move(0, CHARACTER_SPEED / 2);
				}

				if (RIGHT_KEY_PRESSED && !isRightCollision(character, foreground)) {
					if (isCamera()) {
						moveRight();
						goomba0.move(-FOREGROUND_SPEED, 0);
						goomba1.move(-FOREGROUND_SPEED, 0);
						goomba2.move(-FOREGROUND_SPEED, 0);
						if (!ShroomBlock.isAlive) {
							ShroomBlock.object.move(-FOREGROUND_SPEED, 0);
						}
					} else {
						character.move(FOREGROUND_SPEED, 0);
					}
				}

				if (LEFT_KEY_PRESSED && !isLeftCollision(character, foreground)) {
					character.move(-FOREGROUND_SPEED, 0);
					if (character instanceof Homura) {
						moveLeft();
					}
				}

				if (SPACE_KEY_PRESSED && canShoot && !isRightCollision(character, foreground) && character.getHeight() > 60) {
					character.setShootingForward();
					add(
							character.weapon.forward,
							character.getX() + character.weapon.StartPoint.getX(),
							character.getY() + character.weapon.StartPoint.getY()
					);
					new Thread(new WeaponShooter(character.weapon.forward)).start();
					canShoot = false;
					SPACE_KEY_PRESSED = false;
				}

				if (character.weapon.forward.getX() > character.getX() + 800) {
					remove(character.weapon.forward);
					canShoot = true;
					hasWeaponCrashed = false;
				}

				if (hasWeaponCrashed) {
					add(
							new GImage(Weapon.Explosion.getImage()),
							character.weapon.forward.getX(),
							character.weapon.forward.getY() - Weapon.Explosion.getHeight() / 2
					);
					foreground.remove((foreground.getElementAt(foreground.getLocalPoint(Weapon_Crash_Point))));
					remove(character.weapon.forward);
					hasWeaponCrashed = false;
				}

				if (UP_KEY_PRESSED) {
					jump();
				}

				if (character.getBounds().intersects(ShroomBlock.object.getBounds()) && ShroomBlock.object.runner != null) {
					Sound.playLevelup();
					ShroomBlock.object.runner.kill();
					ShroomBlock.object.die();
					initiateNewShroom();
					foreground.add(character, character.getLocation()); // This is totally intentional, guys
					changeCharacter();
					add(character);
				}


				// What have I done
				if (isUpCollision && !isOnGround()) {
					if (ShroomBlock.contains(upIntersectionPoint) && ShroomBlock.isAlive) {
						Sound.playShroom();
						add(ShroomBlock.object, ShroomBlock.getLocation());
						ShroomBlock.moveObject();
						new Thread(ShroomBlock.object.runner = new Runner(ShroomBlock.object, foreground)).start();
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
							Sound.playBump();
							new Thread(new MoveBlock(foreground.getElementAt(foreground.getLocalPoint(upIntersectionPoint)))).start();
						}
						isUpCollision = false;
					}
				}

				if ((IS_FALLING && !isOnGround())) {
					fallDown();
				}

				/*
				  @param SPECIAL_KEY_PRESSED is assigned true by key listener
				 * Special sequence for secret level.
				 * The sequence is a reference to a popular glitch in the game Goldeneye 007
				 * @see http://knowyourmeme.com/memes/get-down-geddan
				 * @see https://gamefaqs.gamespot.com/n64/197462-goldeneye-007/faqs/9961
				 * @see https://www.youtube.com/watch?v=SP5c_MEs9mo
				 */
				if (SPECIAL_KEY_PRESSED) {

					Sound.stopBGM();

					Long current = System.currentTimeMillis();


					GImage yes = new GImage("Images/Special/yes.gif");
					add(
							yes,
							character.getX() - (yes.getWidth() / 2 - character.getWidth() / 2),
							character.getY() - (yes.getHeight() / 2 - character.getWidth() / 2)
					);


					while (System.currentTimeMillis() < current + 4290)
					remove(yes);

					{
						foreground.move(50, -50);
						setBackground(Color.RED);
						pause(100);
						foreground.move(-50, 50);
						setBackground(Color.BLUE);
						pause(100);
						foreground.move(50, -50);
						setBackground(Color.YELLOW);
						pause(100);
						foreground.move(-50, 50);
						setBackground(Color.MAGENTA);
						pause(100);
						foreground.move(50, -50);
						setBackground(Color.ORANGE);
						pause(100);
						foreground.move(50, -50);
						setBackground(Color.WHITE);
						pause(500);
						foreground.move(-50, 50);
						setBackground(Color.PINK);
						pause(100);
						foreground.move(-50, 50);
						setBackground(Color.BLACK);
						stats.setWhite();
						pause(2000);
					}

					Sound.playSpecial();
					pause(1500);
					character.setLocation(100, 100);
					character.setImage("Images/Special/glitchslow.gif");

					goomba0.runner.kill();
					goomba1.runner.kill();
					goomba2.runner.kill();

					remove(goomba0);
					remove(goomba1);
					remove(goomba2);

					current = System.currentTimeMillis();

					while (true) {
						if (System.currentTimeMillis() >= current + 15700) {
							character.setImage("Images/Special/glitchfast.gif");
						}
						moveRight();
						pause(1);
						if (System.currentTimeMillis() >= current + 30000) {
							remove(character);
							character = new Character(Madoka.init(character));
							add(character);
							stats.changeWorld(-1);
							foreground.initiateSecret(character.getLocation());
							break;
						}
					}
				}
				pause(PAUSE_TIME);
			}

			character.setJumpingForward();
			Sound.stopBGM();
			Sound.playDeath();
			die();
			character.setVisible(false);
			pause(4000);
			character.setVisible(true);
			Sound.playBGM();

			character.setLocation(character.getX() - 500, 0);
			if (character.getX() < 0) {
				character.setLocation(0, 0);
			}

			stats.decreaseLives();

		}
		displayGameLostScreen();
	}

	@Override
	public void init() {
		addKeyListeners();
		addMouseListeners();
		setSize(1920, 580);
	}

	@Override
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
							Sound.playJump();
							character.setJumpingForward();
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
				case KeyEvent.VK_F:
					if (character.getHeight() >= 90) {
						SPECIAL_KEY_PRESSED = true;
					}
					break;
				case KeyEvent.VK_W:
					Sound.playSelect();
					break;
				default:
					Sound.playSelect();
					break;
			}
		}
	}

	@Override
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
				case KeyEvent.VK_F:
					SPECIAL_KEY_PRESSED = false;
			}
		}
	}

	private boolean isOnGround() {
		upIntersectionPoint.setLocation(character.getX() + character.getWidth() / 2, character.getY());
		downIntersectionPoint.setLocation(character.getX() + character.getWidth() / 2, character.getY() + character.getHeight());
		return foreground.contains(downIntersectionPoint);
	}

	private boolean isGameOver() {
		return !(character.getY() + character.getHeight() < getHeight());
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

	/**
	 * @param collidee currently moving object
	 * @param collider static object
	 * @return whether collidee collides with collider
	 */
	@SuppressWarnings("AlwaysInverted")
	private boolean isRightCollision(GObject collidee, GObject collider) {
		return (collider.contains(collidee.getX() + collidee.getWidth(), collidee.getY() + collidee.getHeight() / 2) ||
				collider.contains(collidee.getX() + collidee.getWidth(), collidee.getY() + collidee.getHeight() - 10));
	}

	@Deprecated
	@SuppressWarnings("unused")
	private boolean isRightCollisionFoe(GObject main) {
		return ((getElementAt(main.getX() + main.getWidth(), main.getY() + main.getHeight() * 2 / 3)) instanceof Foe) && (((Foe) getElementAt(main.getX() + main.getWidth(), main.getY() + main.getHeight() * 2 / 3)).isInterractible);
	}

	private void moveRight() {
		background.move(-BACKGROUND_SPEED, 0);
		foreground.move(-FOREGROUND_SPEED, 0);
	}

	/**
	 * @param collidee currently moving object
	 * @param collider static object
	 * @return whether collidee collides with collider
	 */
	private boolean isLeftCollision(GObject collidee, GObject collider) {
		return (collider.contains(collidee.getX(), collidee.getY() + collidee.getHeight() / 2) ||
				collider.contains(collidee.getX(), collidee.getY() + collidee.getHeight() - 10));
	}

	private void moveLeft() {
		background.move(BACKGROUND_SPEED, 0);
		foreground.move(FOREGROUND_SPEED, 0);
	}

	private void smallJump() {
		JUMP_AIR_TIME = 70;
		UP_KEY_PRESSED = true;
		IS_FALLING = false;
		jump();
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

	private void initiateNewShroom() {
		ShroomBlock = foreground.getRandomQuestionBlock();
		ShroomBlock.convertToShroom();
		foreground.add(ShroomBlock);
		System.out.println(ShroomBlock.getX());
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

		if (foreground.contains(character.getX() + character.getWidth() / 2, character.getY())) {
			isUpCollision = true;
			return true;
		} else {
			isUpCollision = false;
			return false;
		}
	}

	private void setCharacters() {
		Characters.add(new Mami());
		Characters.add(new Homura());
		Characters.add(Madoka.init(character));
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

	private void removeMenu() {
		remove(StartMenu);
	}

	public static void main(String[] args) {
		new Main().start();
	}
}