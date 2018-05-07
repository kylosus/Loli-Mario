import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;

import java.util.concurrent.Callable;

public class StartMenu extends GCompound {
	private double WIDTH;
	private double HEIGHT;
	private final double REFERENCE_POINT_Y = 75;
	private final double REFERENCE_POINT_X = 75;
	public static String font = "sans-24";
	private GImage Large = Main.character.Large;
	private GImage background = Core.Menu_Background;
	private GImage Left_Bar = Core.Menu_Left_Bar;
	private GImage Right_Bar = Core.Menu_Right_Bar;
	private GLabel name;
	private GLabel age;
	private GLabel weapon;

	private MenuItem Left_Button = new MenuItem(Core.Menu_Left_Button_regular, Core.Menu_Left_Button_hovered, new Callable<Void>() {
		@Override
		public Void call() throws Exception {
			changeCharacter();
			return null;
		}
	});

	private MenuItem Right_Button = new MenuItem(Core.Menu_Right_Button_regular, Core.Menu_Right_Button_hovered, new Callable<Void>() {
		@Override
		public Void call() throws Exception {
			changeCharacter();
			return null;
		}
	});

	private MenuItem Play_Button = new MenuItem(Core.Menu_Button_regular, Core.Menu_Button_hovered, "Play", new Callable<Void>() {
		@Override
		public Void call() throws Exception {
			remove(Main.StartMenu);
			Main.character.setLocation(WIDTH / 3 - Main.character.getWidth(), Main.character.getHeight());
			Main.isInMenu = false;
			return null;
		}
	});

	private MenuItem Map_Button = new MenuItem(Core.Menu_Button_regular, Core.Menu_Button_hovered, "Maps (not available yet)");
	private MenuItem Exit_Button = new MenuItem(Core.Menu_Button_regular, Core.Menu_Button_hovered, "Exit", new Callable<Void>() {
		@Override
		public Void call() throws Exception {
			System.exit(0);
			return null;
		}
	});

	public StartMenu(double WIDTH, double HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		add(background);
		add(Left_Bar, -Left_Bar.getWidth() * 2 / 3, 0);
		add(Right_Bar, WIDTH * 2 / 3, 0);
		add(
				Large,
				(WIDTH - Main.character.Large.getWidth()) / 2,
				0
		);
		add(Play_Button, REFERENCE_POINT_X, REFERENCE_POINT_Y);
		add(Map_Button, REFERENCE_POINT_X, Play_Button.getY() + Play_Button.getHeight());
		add(Exit_Button, REFERENCE_POINT_X, Map_Button.getY() + Map_Button.getHeight());
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
				Left_Bar.getX() + Left_Bar.getWidth(),
				HEIGHT / 2 - Left_Button.getHeight() / 2
		);

		add(
				Right_Button,
				Right_Bar.getX() - Right_Button.getWidth(),
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

	public void changeCharacter() {
		Main.changeCharacter();
		reset();
	}

	private void reset() {
		remove(this);
		add(new StartMenu(WIDTH, HEIGHT));
	}
}
