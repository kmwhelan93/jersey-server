package jsonObjects;

import java.util.Random;

public class Point {
	private static Random random = new Random();
	public int x;
	public int y;
	
	public Point() {
		
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point add(Point p) {
		return new Point(this.x + p.x, this.y + p.y);
	}
	
	public Point scale(int s) {
		return new Point(this.x * s, this.y * s);
	}
	
	public static Point getRandomDirection() {
		int direction = random.nextInt(4);
		return getPoint(direction);
	}
	
	public static Point getPoint(int direction) {
		switch(direction) {
		case 0:
			return new Point(0, 1);
		case 1:
			return new Point(1, 0);
		case 2:
			return new Point(0, -1);
		case 3:
			return new Point(-1, 0);
		default:
			return new Point(0, 1);
		}
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
	
	
}
