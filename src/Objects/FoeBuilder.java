package Objects;

import Builders.Foreground;
import Game.Main;
import acm.graphics.GCompound;
import acm.graphics.GPoint;
import java.util.ArrayList;
import java.util.Iterator;

@Deprecated
@SuppressWarnings("all")
public class FoeBuilder extends GCompound {

	private ArrayList<Foe> Foes = new ArrayList<>();
	private Iterator<Foe> iterator;
	private Foreground foreground;

	public FoeBuilder(Foreground foreground) {
		init();
		iterator = Foes.iterator();
		double dx = 700;
		while (iterator.hasNext()) {
			add(iterator.next(), dx, 100);
			dx += 100;
		}

		for (int i = 0; i < 50; i++) {
			add(new Goomba().init(foreground), dx, 100);
			dx += 100;
		}
	}

	public Foe getObject(Object object) {

		Foe foe;
		while (iterator.hasNext()) {
			foe = iterator.next();
			if (object.equals(foe)) {
				return foe;
			}
		}
		return null;
	}

	public Foe getObjectAt(double x, double y) {
		return (Foe) (getElementAt(x, y));
	}

	public Foe getObjectAt(GPoint point) {
		return (Foe)(getElementAt(point));

	}


	public void init() {
		Foes.add(new Goomba().init(Main.foreground));
		Foes.add(new Goomba().init(Main.foreground));
		Foes.add(new Goomba().init(Main.foreground));
		Foes.add(new Goomba().init(Main.foreground));
		Foes.add(new Goomba().init(Main.foreground));
		Foes.add(new Goomba().init(Main.foreground));
	}


}
