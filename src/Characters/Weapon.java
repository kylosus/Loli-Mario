package Characters;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GPoint;

public class Weapon extends GCompound {
	public GImage forward;
	public GImage backward;
	public GPoint StartPoint;
	public double Speed;
	public GImage Explosion = new GImage("Images/Marios/WeaponExplosion.gif");

	public Weapon() {}
}
