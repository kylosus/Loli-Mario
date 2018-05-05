package Characters;

import Builders.Character;
import acm.graphics.*;

import Actions.*;

public class Madoka extends Character {
	public static String Name = "Kaname Madoka";
	public static String Age = "14";
	public static String WeaponName = "Rose branch bow";
	public static GImage Onscreen = new GImage("Images/Marios/Madoka/MadokaStandStillForward.gif");
	public static GImage StandStillForward = new GImage("Images/Marios/Madoka/MadokaStandStillForward.gif");
	public static GImage StandStillBackward = new GImage("Images/Marios/Madoka/MadokaStandStillBackward.gif");
	public static GImage RunForward = new GImage("Images/Marios/Madoka/MadokaRunForward.gif");
	public static GImage RunBackward = new GImage("Images/Marios/Madoka/MadokaRunBackward.gif");
	public static GImage JumpForward = new GImage("Images/Marios/Madoka/MadokaJumpForward.gif");
	public static GImage JumpBackward = new GImage("Images/Marios/Madoka/MadokaJumpBackward.gif");
	public static GImage ShootForward = new GImage("Images/Marios/Madoka/MadokaShootForward.gif");
	public static GImage ShootBackward = new GImage("Images/Marios/Madoka/MadokaShootBackward.gif");
	public static GImage WeaponForward = new GImage("Images/Marios/Madoka/MadokaWeaponForward.png");
	public static GImage WeaponBackward = new GImage("Images/Marios/Madoka/MadokaWeaponBackward.png");
	public static GImage MadokaLarge = new GImage("Images/Marios/Madoka/MadokaLarge.gif");
	public static GPoint StartPoint = new GPoint(0, 0);

	public String name;
	public String age;
	public Running running;
	public Jumping jumping;
	public Still still;
	public State state;
	public Shooting shooting;
	public Weapon weapon;
	public GImage onscreen;
	public GImage Large;

	public Madoka() {
		running = new Running();
		jumping = new Jumping();
		still = new Still();
		state = new State();
		shooting = new Shooting();
		weapon = new Weapon();
		this.name = Name;
		this.age = Age;
		this.onscreen = Onscreen;
		this.still.state.forward = StandStillForward;
		this.still.state.backward = StandStillBackward;
		this.running.state.forward = RunForward;
		this.running.state.backward = RunBackward;
		this.jumping.state.forward = JumpForward;
		this.jumping.state.backward = JumpBackward;
		this.shooting.state.forward = ShootForward;
		this.shooting.state.backward = ShootBackward;
		this.weapon.name = "Rose branch bow";
		this.weapon.forward = WeaponForward;
		this.weapon.backward = WeaponBackward;
		this.weapon.StartPoint = StartPoint;
		this.Large = MadokaLarge;
	}

	public static Builders.Character init(Builders.Character character) {
		character.setCurrent(StandStillForward);
		character.name = Name;
		character.age = Age;
		character.still.state.forward = StandStillForward;
		character.still.state.backward = StandStillBackward;
		character.running.state.forward = RunForward;
		character.running.state.backward = RunBackward;
		character.jumping.state.forward = JumpForward;
		character.jumping.state.backward = JumpBackward;
		character.shooting.state.forward = ShootForward;
		character.shooting.state.backward = ShootBackward;
		character.weapon.name = WeaponName;
		character.weapon.forward = WeaponForward;
		character.weapon.backward = WeaponBackward;
		character.weapon.StartPoint = StartPoint;
		character.Large = MadokaLarge;
		return character;
	}
}