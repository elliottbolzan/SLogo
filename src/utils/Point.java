package utils;

/**
 * @author Jay Doherty
 * This class packages together x and y double-precision coordinates in a convenient object. 
 */
public class Point {
	private double myX;
	private double myY;
	
	public Point(double x, double y) {
		myX = x;
		myY = y;
	}
	
	public Point(int x, int y) {
		myX = x;
		myY = y;
	}
	
	public Point(Point other) {
		myX = other.getX();
		myY = other.getY();
	}
	
	@Override
	public boolean equals(Object o) {
		return (o != null &&
				o instanceof Point &&
				myX == ((Point)o).getX() &&
				myY == ((Point)o).getY());
	}
	
	public double getX() {
		return myX;
	}
	
	public double getY() {
		return myY;
	}
	
	public void setX(double x) {
		myX = x;
	}
	
	public void setY(double y) {
		myY = y;
	}
}
