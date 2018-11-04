package uet.oop.bomberman.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Tất cả sprite (hình ảnh game) được lưu trữ vào một ảnh duy nhất
 * Class này giúp lấy ra các sprite riêng từ 1 ảnh chung duy nhất đó bằng cách lấy mảng các pixels
 */
public class SpriteSheet {

	private String _path; // path of image resouce file
	public final int SIZE; // 256
	public int[] _pixels; // 256 * 256 pixels
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/classic.png", 256); // an unique object "tiles"
	
	public SpriteSheet(String path, int size) {
		_path = path;
		SIZE = size;
		_pixels = new int[SIZE * SIZE]; // _pixels isn't null
		load();
	}
	
	private void load() {
		try {
			URL a = SpriteSheet.class.getResource(_path);
			// BufferedImage class: describes the image "image" with an accessible buffer of image data
			BufferedImage image = ImageIO.read(a); // read(): returns a BufferedImage as the result of decoding a supplied URL (a) with an ImageReader chosen automatically from among those currently registered.
			int w = image.getWidth(); // image data: the width of the image
			int h = image.getHeight(); // image data: the height of the image
			image.getRGB(0, 0, w, h, _pixels, 0, w); // Returns an array of integer pixels in the default RGB color model (TYPE_INT_ARGB) and default sRGB color space, from a portion of the image data.
			// since w and h are currently the width and the height, all of the image will be scanned
			// the rgb pixels will be written to the _pixels array
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
