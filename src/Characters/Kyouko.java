package Characters;
import Builders.Character;
import acm.graphics.*;

import Actions.*;

public class Kyouko extends Character {

	public Kyouko() { // Experimental
		running = new Running();
		jumping = new Jumping();
		still = new Still();
		name = "Sakura Kyouko";
		Onscreen = new GImage("Images/Marios/Kyouko/KyoukoStandStillForward.gif");
		this.still.state.forward = new GImage("Images/Marios/Kyouko/KyoukoStandStillForward.gif");
		this.still.state.backward = new GImage("Images/Marios/Kyouko/KyoukoStandStillBackward.gif");
		this.running.state.forward = new GImage("Images/Marios/Kyouko/KyoukoRunForward.gif");
		this.running.state.backward = new GImage("Images/Marios/Kyouko/KyoukoRunBackward.gif");
		this.jumping.state.forward = new GImage("Images/Marios/Kyouko/KyoukoTestGif.gif");
		this.jumping.state.backward = new GImage("Images/Marios/Kyouko/KyoukoTestGif.gif");
	}

}