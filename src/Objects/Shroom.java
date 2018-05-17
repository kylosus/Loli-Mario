package Objects;

import Builders.Core;
import acm.graphics.GImage;

class Shroom extends PowerUpObject {

	Shroom() {
		super(Core.Mushroom_red, 1000);
		add(image);
		this.isShroom = true;
	}
}
