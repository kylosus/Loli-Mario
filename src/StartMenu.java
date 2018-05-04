import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartMenu extends GCompound {
	private GImage background = new GImage("Images/DDLC/gui/menu_bg.png");

	public StartMenu() {
		add(background);

	}
}
