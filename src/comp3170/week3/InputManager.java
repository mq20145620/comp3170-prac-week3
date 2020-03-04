package comp3170.week3;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class InputManager implements KeyListener {

	private Set<Integer> keysDown;
	
	public InputManager() {
		keysDown = new HashSet<Integer>();
	}

	public boolean isKeyDown(int keyCode) {
		return keysDown.contains(keyCode);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keysDown.add(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keysDown.remove(e.getKeyCode());
	}

}
