package Game;

import acm.graphics.GCompound;
import acm.graphics.GLabel;

import java.awt.*;

public class ScoreItem extends GCompound {
	public GLabel label;
	public int value;

	public ScoreItem(Font font) {
		value = 0;
		label = new GLabel(Integer.toString(value));
		label.setFont(font);
		add(label);
	}

	public ScoreItem(Font font, int initialValue) {
		value = initialValue;
		label = new GLabel(Integer.toString(value));
		label.setFont(font);
		add(label);
	}

	public void startTimer() {
		new Thread(() -> {
			while (this.value > 0) {
				pause(1000);
				this.iterateBy(-1);
			}
			System.exit(0);
		}).start();
	}


	public void iterateOnce() {
		this.value = this.value + 1;
		this.setLabel();
	}

	public void iterateBy(int value) {
		this.value = this.value + value;
		this.setLabel();
	}

	public void setLabel() {
		this.label.setLabel(Integer.toString(this.value));
	}
}
