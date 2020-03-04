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

	/**
	 * Test if the specified key is currently proessed.
	 * Note: the input is a keycode value, as specified on the KeyEvent class.
	 * https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html
	 * 
	 * So, for instance, to test if the up arrow is pressed call:
	 * 
	 * 		input.isKeyDown(KeyEvent.VK_UP)
	 * 
	 * @param keyCode The integer keycode for the key 
	 * @return true if the key is pressed
	 */
	
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
