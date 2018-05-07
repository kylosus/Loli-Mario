package Characters;
import Builders.Character;
import acm.graphics.*;

import Actions.*;

public class Mami extends Character {

	public Mami() {
		running = new Running();
		jumping = new Jumping();
		still = new Still();
		name = "Tomoe Mami";
		Onscreen = new GImage("Images/Marios/Mami/MamiStandStillForward.gif");
		this.Large = new GImage("Images/Marios/Mami/MamiLarge.gif");
		this.still.state.forward = new GImage("Images/Marios/Mami/MamiStandStillForward.gif");
		this.still.state.backward = new GImage("Images/Marios/Mami/MamiStandStillBackward.gif");
		this.running.state.forward = new GImage("Images/Marios/Mami/MamiRunForward.gif");
		this.running.state.backward = new GImage("Images/Marios/Mami/MamiRunBackward.gif");
		this.jumping.state.forward = new GImage("Images/Marios/Mami/MamiRunForward.gif");
		this.jumping.state.backward = new GImage("Images/Marios/Mami/MamiRunBackward.gif");
		this.shooting.state.forward = new GImage("Images/Marios/Mami/MamiShootForward.gif");
		this.weapon.forward = new GImage("Images/Marios/Mami/WeaponForward.png");
	}

}