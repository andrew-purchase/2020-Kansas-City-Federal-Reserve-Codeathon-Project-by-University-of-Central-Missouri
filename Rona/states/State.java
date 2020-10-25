package states;

import java.awt.Graphics;

public abstract class State {

	private static State currentState = null;

	public State() {}

	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public static void setCurrentStateAt(State[] state, int s) {
		setCurrentState(state[s]);
	}

	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State currentState) {
		State.currentState = currentState;
	}

} // end class State
