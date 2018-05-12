import acm.graphics.GCompound;
import acm.graphics.GImage;

public class QuestionBlock extends GCompound {

	private GImage block = new GImage("Images/OBJ/QuestionBlock_alive.gif");
	private final double SPEED = 5;

	public QuestionBlock() {
		add(block);
	}


	public void jump() {
		for (int i = 0; i < 5; i++) {
			this.move(0, -SPEED);
		}
	}
}
