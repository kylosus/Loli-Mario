package Objects;

import Builders.Core;
import Builders.Foreground;
import Game.Main;
import acm.graphics.GCompound;
import acm.graphics.GImage;

public class QuestionBlock extends GCompound {
	public GImage alive = new GImage(Core.QuestionBlock_alive);
	public GImage dead = new GImage(Core.QuestionBlock_dead);
	public PowerUpObject object;
	public boolean isAlive = true;

	public QuestionBlock() {
		this.object = new PowerUpObject(Core.Coin, 100);
		add(alive);
	}

	public QuestionBlock(Shroom shroom) {
		this.object = shroom;
		add(alive);
	}

	public QuestionBlock(String image) {
		this.object = new PowerUpObject(image);
	}

	public PowerUpObject moveObject() {
		if (isAlive) {
			isAlive = false;
			if (!this.object.isShroom) {
				add(this.object);
				object.sendBackward();
				new Thread(() -> {
					for (int i = 10; i != 0; i--) {
						object.move(0, -7);
						pause(25);
					}
					for (int i = 10; i != 0; i--) {
						object.move(0, 7);
						pause(25);
					}
					remove(this.object);
				}).start();
			} else {
//				object.sendToFront();
//				System.out.println("Object started");
				new Thread(() -> {
					for (int i = (int)object.getHeight(); i != 0; i--) {
						object.move(0, -1);
						pause(10);
					}
				}).start();
//				pause(10 * object.getHeight());
//				new Thread(new Runner(object, Main.foreground)).start();
			}
			this.alive.setImage(dead.getImage());
			return object;
		}
		return null;
	}

	public void convertToShroom() {
		this.object = new Shroom();
	}
}
