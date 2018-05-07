import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Callable;

public class MenuItem extends GCompound {
	private GImage Onscreen;
	private String regular;
	private String hovered;
	private String text;
	private Callable<Void> clickMethod;

	public MenuItem(String regular, String hovered, Callable<Void> clickMethod) {
		this.regular = regular;
		this.hovered = hovered;
		Onscreen = new GImage(this.regular);
		add(Onscreen);
		addListeners();
		this.clickMethod = clickMethod;
	}

	public MenuItem(String regular, String hovered, String text) {
		this.regular = regular;
		this.hovered = hovered;
		Onscreen = new GImage(this.regular);
		add(Onscreen);
		GLabel Button_Text = new GLabel(text);
		Button_Text.setFont(StartMenu.font);
		add(
				Button_Text,
				(Onscreen.getWidth() - Button_Text.getWidth()) / 2,
				(Onscreen.getHeight() + Button_Text.getHeight() / 2) / 2
		);
		addListeners();
	}

	public MenuItem(String regular, String hovered, String text, Callable<Void> clickMethod) {
		this.regular = regular;
		this.hovered = hovered;
		Onscreen = new GImage(this.regular);
		add(Onscreen);
		GLabel Button_Text = new GLabel(text);
		Button_Text.setFont(StartMenu.font);
		add(
				Button_Text,
				(Onscreen.getWidth() - Button_Text.getWidth()) / 2,
				(Onscreen.getHeight() + Button_Text.getHeight() / 2) / 2
		);
		this.clickMethod = clickMethod;
		addListeners();
	}

	public void addListeners() {
		Onscreen.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (clickMethod != null) {
					try {
						clickMethod.call();
						System.out.println("Click successful");
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Onscreen.setImage(hovered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Onscreen.setImage(regular);
			}
		});
	}
}
