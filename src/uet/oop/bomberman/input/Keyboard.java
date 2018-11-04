package uet.oop.bomberman.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Tiếp nhận và xử lý các sự kiện nhập từ bàn phím
 */
// (Tại sao không thừa kế KeyEvent?)
public class Keyboard implements KeyListener {
	
	private boolean[] keys = new boolean[120]; //120 is enough to this game
	public boolean up, down, left, right, space; // khi phím được nhấn, các biến nhận giá trị true
	
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_X];
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	// Invoked when a key has been pressed
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}

	// Invoked when a key has been released
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

}
