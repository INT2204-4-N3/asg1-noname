package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.tile.destroyable.DestroyableTile;
import uet.oop.bomberman.graphics.Screen;

public class Flame extends Entity {

	protected Board _board;
	protected int _direction;
	private int _radius;
	protected int xOrigin, yOrigin;
	protected FlameSegment[] _flameSegments = new FlameSegment[0];

	/**
	 *
	 * @param x hoành độ bắt đầu của Flame
	 * @param y tung độ bắt đầu của Flame
	 * @param direction là hướng của Flame
	 * @param radius độ dài cực đại của Flame
	 */
	public Flame(int x, int y, int direction, int radius, Board board) {
		xOrigin = x;
		yOrigin = y;
		_x = x;
		_y = y;
		_direction = direction;
		_radius = radius;
		_board = board;

		createFlameSegments();
	}

	/**
	 * Tạo các FlameSegment, mỗi segment ứng một đơn vị độ dài
	 */
	private void createFlameSegments() {
		/**
		 * tính toán độ dài Flame, tương ứng với số lượng segment
		 */
		_flameSegments = new FlameSegment[calculatePermitedDistance()];

		/**
		 * biến last dùng để đánh dấu cho segment cuối cùng
		 */
		boolean last = false;
		int n = _flameSegments.length;

		// TODO: tạo các segment dưới đây
		for(int i = 0; i < n; i++) {
			if(i == n - 1) {
				last = true;
			}
			switch (_direction) {
				case 0: // up
					_flameSegments[i] = new FlameSegment((int)_x, (int)_y - 1 - i, _direction, last);
					break;
				case 1: // right
					_flameSegments[i] = new FlameSegment((int)_x + 1 + i, (int)_y, _direction, last);
					break;
				case 2: // down
					_flameSegments[i] = new FlameSegment((int)_x, (int)_y + 1 + i, _direction, last);
					break;
				case 3: // left
					_flameSegments[i] = new FlameSegment((int)_x - 1 - i, (int)_y, _direction, last);
					break;
			}

		}
	}

	/**
	 * Tính toán độ dài của Flame, nếu gặp vật cản là Brick/Wall, độ dài sẽ bị cắt ngắn
	 * @return
	 */
	private int calculatePermitedDistance() {
		// TODO: thực hiện tính toán độ dài của Flame
		int length = 0;
		Entity e;
		int x = xOrigin;
		int y = yOrigin;

		while(length <= _radius - 1) {
			if(_direction == 0) y--;
			if(_direction == 1) x++;
			if(_direction == 2) y++;
			if(_direction == 3) x--;
			e = _board.getEntity(x, y, null);
			if(!e.collide(this)) {
				//if(e instanceof DestroyableTile) length++;
				break;
			}
			length++;
		}
		return length;
	}
	
	public FlameSegment flameSegmentAt(int x, int y) {
		for (int i = 0; i < _flameSegments.length; i++) {
			if(_flameSegments[i].getX() == x && _flameSegments[i].getY() == y)
				return _flameSegments[i];
		}
		return null;
	}

	@Override
	public void update() {}
	
	@Override
	public void render(Screen screen) {
		for (int i = 0; i < _flameSegments.length; i++) {
			_flameSegments[i].render(screen);
		}
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý va chạm với Bomber, Enemy. Chú ý đối tượng này có vị trí chính là vị trí của Bomb đã nổ
		if(e instanceof Character) {
			((Character) e).kill();
		}
		return true;
	}
}
