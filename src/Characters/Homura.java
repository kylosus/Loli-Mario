package Characters;
import Builders.Character;
import acm.graphics.*;

import Actions.*;

public class Homura extends Character {

	public Homura() { // Experimental
		running = new Running();
		jumping = new Jumping();
		still = new Still();
		name = "Akemi Homura";
		Onscreen = new GImage("Images/Marios/Homura/HomuraStandStillForward.gif");
		this.still.state.forward = new GImage("Images/Marios/Homura/HomuraStandStillForward.gif");
		this.still.state.backward = new GImage("Images/Marios/Homura/HomuraStandStillForward.gif");
		this.running.state.forward = new GImage("Images/Marios/Homura/HomuraRunForward.gif");
		this.running.state.backward = new GImage("Images/Marios/Homura/HomuraRunBackward.gif");
		this.jumping.state.forward = new GImage("Images/Marios/Homura/HomuraStandStillForward.gif");
		this.jumping.state.backward = new GImage("Images/Marios/Homura/HomuraStandStillForward.gif");
		this.shooting.state.forward = new GImage("Images/Marios/Homura/HomuraShootForward.gif");
		this.shooting.state.backward = new GImage("Images/Marios/Homura/HomuraShootBackward.gif");
		this.weapon.forward = new GImage("Images/Marios/Homura/Weapon.png");
		this.weapon.backward = new GImage("Images/Marios/Homura/Weapon.png");
		this.weapon.StartPoint = new GPoint(90, 33);
	}

}