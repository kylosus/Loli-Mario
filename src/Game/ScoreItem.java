package Game;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import java.awt.*;

class ScoreItem extends GCompound {
	private GLabel label;
	private int value;

	ScoreItem(Font font) {
		value = 0;
		label = new GLabel(Integer.toString(value));
		label.setFont(font);
		add(label);
	}

	ScoreItem(Font font, int initialValue) {
		value = initialValue;
		label = new GLabel(Integer.toString(value));
		label.setFont(font);
		add(label);
	}

	void startTimer() {
		new Thread(() -> {
			while (this.value > 0) {
				pause(1000);
				this.iterateBy(-1);
			}
			System.exit(0);
		}).start();
	}


	void iterateOnce() {
		this.value = this.value + 1;
		this.setLabel();
	}

	void iterateBy(int value) {
		this.value = this.value + value;
		this.setLabel();
	}

	void setValue(int value) {
		this.value = value;
		this.setLabel();
	}

	private void setLabel() {
		this.label.setLabel(Integer.toString(this.value));
	}
}
