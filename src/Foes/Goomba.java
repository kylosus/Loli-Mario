package Foes;

import Builders.Core;
import acm.graphics.GImage;

public class Goomba extends Foe {

	public Goomba() {
		super(new GImage(Core.Foes_Goomba_alive), new GImage(Core.Foes_Goomba_dead));
		add(Onscreen);
	}
}
