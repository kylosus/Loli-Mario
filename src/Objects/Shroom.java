package Objects;

import Builders.Core;
import acm.graphics.GImage;

public class Shroom extends PowerUpObject {

	public Shroom() {
		super(Core.Mushroom_red, 1000);
		add(image);
		this.isShroom = true;
	}
}
