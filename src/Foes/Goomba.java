package Foes;

import Builders.Core;
import Builders.Foreground;
import acm.graphics.GImage;

public class Goomba extends Foe {

	public Goomba(Foreground foreground) {
		super(new GImage(Core.Foes_Goomba_alive), new GImage(Core.Foes_Goomba_dead), new GImage(Core.Points_100));
		add(Onscreen);
	}
}
