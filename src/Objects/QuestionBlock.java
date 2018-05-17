package Objects;

import Builders.Core;
import Game.Main;
import Game.Sound;
import acm.graphics.GCompound;
import acm.graphics.GImage;

@SuppressWarnings("unused")
public class QuestionBlock extends GCompound {
	private GImage alive = new GImage(Core.QuestionBlock_alive);
	private GImage dead = new GImage(Core.QuestionBlock_dead);
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

	public void moveObject() {
		if (isAlive) {
			isAlive = false;
			if (!this.object.isShroom) {
				Sound.playCoin();
				Main.stats.getCoin();
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
				new Thread(() -> {
					for (int i = (int)object.getHeight(); i != 0; i--) {
						object.move(0, -1);
						pause(10);
					}
				}).start();
			}
			this.alive.setImage(dead.getImage());
		}
	}

	public void convertToShroom() {
		this.object = new Shroom();
	}
}
