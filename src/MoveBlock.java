import acm.graphics.GObject;
import acm.program.GraphicsProgram;

public class MoveBlock extends GraphicsProgram implements Runnable {

	private GObject object;

	public MoveBlock(GObject object) {
		this.object = object;
	}

	@Override
	public void run() {
		for (int i = 0; i < 7; i++) {
			object.move(0, -3);
			pause(5);
		}
		for (int i = 0; i < 7; i++) {
			object.move(0, 3);
			pause(5);
		}
	}
}
