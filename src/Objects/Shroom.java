package Objects;

import Builders.Core;
import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Shroom extends GCompound {
	public Shroom() {
		add(new GImage(Core.Mushroom_red));
	}
}
