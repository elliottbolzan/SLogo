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
	
	public double getX() {
		return myX;
	}

	public double getY() {
		return myY;
	}

}
