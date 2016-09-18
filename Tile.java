package five;

public class Tile {

	private Turn color;
	private int x;
	private int y;

	public Tile(Turn color, int x, int y) {
		if (x < 0 || x > 9) throw new IllegalArgumentException("Error for x");
		if (y < 0 || y > 9) throw new IllegalArgumentException("Error for y");
		this.color = color;
		this.x = x;
		this.y = y;
	}

	public int[] coordinates() {
		int[] ret = {x, y};
		return ret;
	}

	public Turn color() {
		return color;
	}

}
