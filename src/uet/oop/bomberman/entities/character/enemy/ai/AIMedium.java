package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;

public class AIMedium extends AI {
	Bomber _bomber;
	Enemy _e;
	
	public AIMedium(Bomber bomber, Enemy e) {
		_bomber = bomber;
		_e = e;
	}

	@Override
	public int calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi
		// Assume player has died, then almedium can't follow player
        if(_bomber == null) {
            return random.nextInt(4);
        }
        int ran = random.nextInt(2);
        if(ran == 1) {
            if(calCol() != -1) {
                return calCol();
            } else {
                return calRow();
            }
        } else {
            if(calRow() != -1) {
                return calRow();
            } else {
                return calCol();
            }
        }
	}


	private int calCol() {
		if(_e.getXTile() > _bomber.getXTile()) {
			return 3; // left
		} else if (_e.getXTile() < _bomber.getXTile()) {
			return 1;
		} else {
			return -1;
		}
	}

	private int calRow() {
		if(_e.getYTile() > _bomber.getYTile()) {
			return 0; // up
		} else if(_e.getYTile() < _bomber.getYTile()) {
			return 2;
		} else {
			return -1;
		}
	}
}
