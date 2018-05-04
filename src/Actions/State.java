package Actions;

import acm.graphics.*;

import java.awt.*;

import acm.program.*;

public class State {
	public GImage forward;
	public GImage backward;

	public State() {
	}

	public State(String forward, String backward) {
		this.forward = new GImage(forward);
		this.backward = new GImage(backward);
	}
}