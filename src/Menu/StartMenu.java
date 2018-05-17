package Menu;

import Builders.Core;
import Game.Main;
import Game.Sound;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;

public class StartMenu extends GCompound {
	private double WIDTH;
	private double HEIGHT;
	private final double REFERENCE_POINT_Y = 75;
	static String font = "sans-24";
	private GImage Large = Main.character.Large;
	private GLabel name;
	private GLabel age;
	private GLabel weapon;

	private MenuItem Left_Button = new MenuItem(Core.Menu_Left_Button_regular, Core.Menu_Left_Button_hovered, () -> {
		changeCharacter();
		return null;
	});

	private MenuItem Right_Button = new MenuItem(Core.Menu_Right_Button_regular, Core.Menu_Right_Button_hovered, () -> {
		changeCharacter();
		return null;
	});

	private MenuItem Music_Button = new MenuItem(Core.Menu_Button_regular, Core.Menu_Button_hovered, "Music", () -> {
		switchMusic();
		return null;
	});

	public StartMenu(double WIDTH, double HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		GImage background = Core.Menu_Background;
		add(background);
		GImage left_Bar = Core.Menu_Left_Bar;
		add(left_Bar, -left_Bar.getWidth() * 2 / 3, 0);
		GImage right_Bar = Core.Menu_Right_Bar;
		add(right_Bar, WIDTH * 2 / 3, 0);
		add(
				Large,
				(WIDTH - Main.character.Large.getWidth()) / 2,
				0
		);
		MenuItem play_Button = new MenuItem(Core.Menu_Button_regular, Core.Menu_Button_hovered, "Play", () -> {
			Main.character.setLocation(WIDTH / 3 - Main.character.getWidth(), Main.character.getHeight());
			Main.isInMenu = false;
			return null;
		});
		double REFERENCE_POINT_X = 75;
		add(play_Button, REFERENCE_POINT_X, REFERENCE_POINT_Y);
		add(Music_Button, REFERENCE_POINT_X, play_Button.getY() + play_Button.getHeight());
		MenuItem exit_Button = new MenuItem(Core.Menu_Button_regular, Core.Menu_Button_hovered, "Exit", () -> {
			System.exit(0);
			return null;
		});
		add(exit_Button, REFERENCE_POINT_X, Music_Button.getY() + Music_Button.getHeight());
		setGLabels();
		add(name);
		add(age);
		add(weapon);
		add(
				Main.character.weapon.forward,
				weapon.getX(),
				weapon.getY() + weapon.getHeight()
		);

		add(
				Left_Button,
				left_Bar.getX() + left_Bar.getWidth(),
				HEIGHT / 2 - Left_Button.getHeight() / 2
		);

		add(
				Right_Button,
				right_Bar.getX() - Right_Button.getWidth(),
				HEIGHT / 2 - Left_Button.getHeight() / 2
		);
	}

	private void setGLabels() {
		name = new GLabel(
				"Name: " + Main.character.name,
				WIDTH * 2 / 3 + 260,
				REFERENCE_POINT_Y * 2
		);
		name.setFont(font);

		age = new GLabel(
				"Age: " + Main.character.age,
				name.getX(),
				name.getY() + name.getHeight()
		);
		age.setFont(name.getFont());

		weapon = new GLabel(
				"Weapon: " + Main.character.weapon.name,
				age.getX(),
				age.getY() + age.getHeight()
		);
		weapon.setFont(age.getFont());
	}

	private void changeCharacter() {
		Main.changeCharacter();
		reset();
	}

	private void changeBGM() {
		Sound.changeBGM();
	}

	private void switchCharacter() {
		System.out.println("Switched to music");
		Left_Button.setClickMethod(() -> {
			changeBGM();
			return null;
		});
		Right_Button.setClickMethod(() -> {
			changeBGM();
			return null;
		});
		Music_Button.setClickMethod(() -> {
			switchMusic();
			return null;
		});
	}

	private void switchMusic() {
		Large.setImage("Images/note.png");
		add(
				Large,
				(WIDTH - Large.getWidth()) / 2 + 15,
				(HEIGHT - Large.getHeight()) / 2
		);
		Left_Button.setClickMethod(() -> {
			changeBGM();
			return null;
		});
		Right_Button.setClickMethod(() -> {
			changeBGM();
			return null;
		});
		Music_Button.setClickMethod(() -> {
			switchCharacter();
			return null;
		});
	}

	private void reset() {
		remove(this);
		add(new StartMenu(WIDTH, HEIGHT));
	}
}
