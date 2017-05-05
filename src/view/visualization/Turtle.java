package view.visualization;

import utils.Point;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * @author Jay Doherty
 * This class encapsulates all of the Turtle's behavior into an interface. Methods called on this
 * class can move the turtle around the display.
 */
public class Turtle {
	private TurtleDisplay myDisplay;
	private int myID;
	private SimpleBooleanProperty isActiveProperty;

	private Graphic myTurtleGraphic;
	private Pen myPen;
	private Compass myCompass;
	private Schedule mySchedule;

	private SimpleBooleanProperty isMovingProperty;
	private int myStepsRemaining;
	private Point myStepSize;

	protected Turtle(TurtleDisplay home, int ID, Image image) {
		myDisplay = home;
		myID = ID;
		isActiveProperty = new SimpleBooleanProperty(true);
		
		myTurtleGraphic = new Graphic(myDisplay, image, 1);
		myTurtleGraphic.bindOpacityTo(isActiveProperty);
		
		myPen = new Pen(myDisplay, true, Color.BLACK, 1, 1.0);
		myCompass = new Compass();
		mySchedule = new Schedule();
		
		isMovingProperty = new SimpleBooleanProperty(false);
		
		this.setLocation(new Point(0, 0));
		this.setRotation(90.0);
	}

	/**
	 * @return turtle ID
	 */
	public int getID() {
		return myID;
	}

	/**
	 * Moves the turtle to a point on the display
	 * @param point : destination
	 */
	public void moveTo(Point point) {
		myDisplay.moveTurtle(this, point);
	}
	
	/**
	 * Turns the turtle by an amount in degrees
	 * @param degrees : amount of rotation in degrees
	 */
	public void turn(double degrees) {
		this.setRotation(myCompass.getHeading() + degrees);
	}

	/**
	 * @return the current location that the turtle is traveling towards if moving. Stationary 
	 * turtles return their current location.
	 */
	public Point getDestination() {
		return mySchedule.getDestination();
	}

	/**
	 * @return the turtle's current heading
	 */
	public double getRotation() {
		return myCompass.getHeading();
	}

	/**
	 * @return the status of the pen (up or down)
	 */
	public boolean isPenDown() {
		return myPen.isDown();
	}
	
	/**
	 * Sets the status of the pen (up or down)
	 * @param down : boolean specifying if the pen is set down or up
	 */
	public void setPenDown(boolean down) {
		myPen.setDown(down);
	}

	/**
	 * @return the pen's color
	 */
	public Color getPenColor() {
		return myPen.getColor();
	}
	
	/**
	 * @param color : color to set the pen
	 */
	public void setPenColor(Color color) {
		myPen.setColor(color);
	}
	
	/**
	 * @return the index of the color in the color picker table
	 */
	public int getPenColorIndex() {
		return myPen.getColorIndex();
	}
	
	/**
	 * @param index : the index of the color in the color picker table
	 */
	public void setColorIndex(int index) {
		myPen.setColorIndex(index);
	}

	/**
	 * @param width : font size to set the lines
	 */
	public void setPenWidth(double width) {
		myPen.setThickness(width);
	}
	
	/**
	 * @return the turtle's visibility
	 */
	public boolean isVisible() {
		return myTurtleGraphic.isVisible();
	}
	
	/**
	 * @param visible : the turtle's visibility
	 */
	public void setVisible(boolean visible) {
		myTurtleGraphic.setVisible(visible);
	}
	
	/**
	 * Sets the turtle's image given a fully specified image path
	 * @param url : full image path
	 */
	public void setImage(String url) {
		myTurtleGraphic.setImage(url);
		myTurtleGraphic.setCenter(myCompass.getLocation());
	}

	/**
	 * @return index of turtle's image in the image table
	 */
	public int getShapeIndex() {
		return myTurtleGraphic.getIndex();
	}
	
	/**
	 * @param index : index of turtle's image in the image table
	 */
	public void setShapeIndex(int index) {
		myTurtleGraphic.setIndex(index);
	}

	public ImageView getView() {
		return myTurtleGraphic.getView();
	}
	
	protected BooleanProperty activeProperty() {
		return isActiveProperty;
	}
	
	protected BooleanProperty isMovingProperty() {
		return isMovingProperty;
	}

	protected ReadOnlyBooleanProperty readOnlyPenDownProperty() {
		return myPen.readOnlyIsDownProperty();
	}

	protected ReadOnlyDoubleProperty readOnlyXProperty() {
		return myCompass.readOnlyXProperty();
	}

	protected ReadOnlyDoubleProperty readOnlyYProperty() {
		return myCompass.readOnlyYProperty();
	}

	protected ReadOnlyDoubleProperty readOnlyRotationProperty() {
		return myCompass.readOnlyRotationProperty();
	}

	protected Schedule getSchedule() {
		return mySchedule;
	}
	
	protected void setDestination(Point destination, double speed) {
		isMovingProperty.set(true);
		double distX = destination.getX() - myCompass.getX();
		double distY = destination.getY() - myCompass.getY();
		double stepsToDestination = getNumStepsAlongPath(distX, distY, speed);

		myStepsRemaining = (int) (stepsToDestination);
		double myStepSizeX = distX / stepsToDestination;
		double myStepSizeY = distY / stepsToDestination;
		myStepSize = new Point(myStepSizeX, myStepSizeY);

		mySchedule.setDestination(destination);
	}

	protected void updateMovement() {
		if (myStepsRemaining > 0) {
			this.stepTowardsDestination();
			myStepsRemaining--;
		} else {
			isMovingProperty.set(false);
		}
	}
	
	private void setLocation(Point point) {
		myCompass.setX(point.getX());
		myCompass.setY(point.getY());
		myTurtleGraphic.setCenter(point);
	}
	
	private void stepTowardsDestination() {
		Point step = new Point(myCompass.getX() + myStepSize.getX(), myCompass.getY() + myStepSize.getY());

		if (myPen.isDown()) {
			Point adjustedLoc = myCompass.getLocation();
			Point adjustedStep = step;
			if (!myDisplay.isInBounds(adjustedLoc) && !myDisplay.isInBounds(adjustedStep)) {
				adjustedLoc = myDisplay.wrapIntoView(adjustedLoc);
				adjustedStep = myDisplay.wrapIntoView(adjustedStep);
			}
			if (areInSameQuadrant(adjustedLoc, adjustedStep)) {
				myPen.drawLine(adjustedLoc, adjustedStep);
			}
		}

		this.setLocation(step);
	}

	private void setRotation(double degrees) {
		myCompass.setHeading(degrees % 360);
		myTurtleGraphic.setRotation(degrees);
	}
	
	private boolean areInSameQuadrant(Point a, Point b) {
		return (a.getX() * b.getX() >= 0) && (a.getY() * b.getY() >= 0);
	}

	private double getNumStepsAlongPath(double distanceX, double distanceY, double stepLength) {
		if (Math.abs(distanceY) >= Math.abs(distanceX)) {
			return Math.abs(distanceY / stepLength);
		} else {
			return Math.abs(distanceX / stepLength);
		}
	}
}
