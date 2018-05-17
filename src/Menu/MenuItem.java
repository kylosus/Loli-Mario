package Menu;

import Game.Sound;
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
	private Callable<Void> clickMethod;

	MenuItem(String regular, String hovered, Callable<Void> clickMethod) {
		this.regular = regular;
		this.hovered = hovered;
		Onscreen = new GImage(this.regular);
		add(Onscreen);
		addListeners();
		this.clickMethod = clickMethod;
	}

	@SuppressWarnings("unused")
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

	MenuItem(String regular, String hovered, String text, Callable<Void> clickMethod) {
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

	private void addListeners() {
		Onscreen.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (clickMethod != null) {
					Sound.playSelect();
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
				Sound.playHover();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Onscreen.setImage(regular);
				Sound.playHover();
			}
		});
	}

	void setClickMethod(Callable<Void> clickMethod) {
		System.out.println("Placeholder");
		this.clickMethod = clickMethod;
	}

	@Deprecated
	@SuppressWarnings("all")
	void setText(String text) {
		String text1 = text;
	}
}
