package Builders;

import Actions.*;

import Characters.Madoka;
import Characters.Weapon;
import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Character extends GCompound {
	private Character character;
	private static GImage current;
	public Running running;
	public Jumping jumping;
	public Still still;
	public Shooting shooting;
	public String name;
	public String age;
	public GImage Onscreen;
	public Weapon weapon;
	public GImage Large;
	public double Speed = 7;

	public Character() {
		running = new Running();
		jumping = new Jumping();
		still = new Still();
		shooting = new Shooting();
		weapon = new Weapon();
		Large = new GImage("");
	}

	public Character(Character character) {
		this.character = character;
		running = new Running();
		jumping = new Jumping();
		still = new Still();
		shooting = new Shooting();
		weapon = new Weapon();

		this.name = character.name;
		this.age = character.age;
		this.still.state.forward = character.still.state.forward;
		this.still.state.backward = character.still.state.backward;
		this.running.state.forward = character.running.state.forward;
		this.running.state.backward = character.running.state.backward;
		this.jumping.state.forward = character.jumping.state.backward;
		this.weapon.forward = character.weapon.forward;
		this.weapon.backward = character.weapon.backward;
		this.shooting.state.forward = character.shooting.state.forward;
		this.shooting.state.backward = character.shooting.state.backward;
		this.weapon.name = character.weapon.name;
		this.weapon.StartPoint = character.weapon.StartPoint;
		this.Large = character.Large;
		current = new GImage(this.still.state.forward.getImage());
		add(current);
	}


	public void setStandStillForward() {
		current.setImage(this.still.state.forward.getImage());
	}

	public void setStandStillBackward() {
		current.setImage(this.still.state.backward.getImage());
	}

	public void setRunningForward() {
		current.setImage(this.running.state.forward.getImage());
	}

	public void setRunningBackward() {
		current.setImage(this.running.state.backward.getImage());

	}

	public void setCurrent(GImage image) {
		current = image;
	}

	public void jumpForward() {
		current.setImage(this.jumping.state.forward.getImage());
	}

	public void setJumpingBackward() {
		current.setImage(character.jumping.state.backward.getImage());
	}

	public void setShootingForward() {
		current.setImage(this.shooting.state.forward.getImage());
	}
}
















