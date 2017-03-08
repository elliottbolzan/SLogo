package view.visualization;

import utils.Point;

import java.util.LinkedList;
import java.util.Queue;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * @author Jay Doherty
 *
 */
public class Turtle {
	private final static String BASIC_IMAGE = "view/visualization/turtle_1.png";
	private ImageView myImageView;

	private TurtleDisplay myDisplay;
	private int myID;

	private SimpleDoubleProperty myXProperty;
	private SimpleDoubleProperty myYProperty;
	private Point myDestination;
	private SimpleDoubleProperty myRotationProperty;
	private Queue<Point> myFutureDestinations;

	private SimpleBooleanProperty myPenDownProperty;
	private Color myPenColor;
	private double myPenWidth;

	private SimpleBooleanProperty isMovingProperty;
	private int myStepsRemaining;
	private Point myStepSize;

	public Turtle(int id, TurtleDisplay home) {
		
		myImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(BASIC_IMAGE)));
		myFutureDestinations = new LinkedList<Point>();
		
		myDisplay = home;
		myID = id;

		myPenDownProperty = new SimpleBooleanProperty(true);
		myPenColor = Color.BLACK;
		myPenWidth = 1.0;

		myXProperty = new SimpleDoubleProperty();
		myYProperty = new SimpleDoubleProperty();
		this.setLocation(new Point(0, 0));
		myDestination = new Point(0, 0);
		
		myRotationProperty = new SimpleDoubleProperty();
		this.setRotation(90.0);
		isMovingProperty = new SimpleBooleanProperty(false);
	
	}

	public int getID() {
		return myID;
	}
	
	public Point getDestination() {
		return myDestination;
	}

	public double getRotation() {
		return myRotationProperty.get();
	}

	public boolean isPenDown() {
		return myPenDownProperty.get();
	}
	
	public boolean isVisible() {
		return myImageView.isVisible();
	}
	
	public Color getPenColor() {
		return myPenColor;
	}

	protected Point getCurrentLocation() {
		return new Point(myXProperty.get(), myYProperty.get());
	}
	
	protected SimpleDoubleProperty xProperty() {
		return myXProperty;
	}
	
	protected SimpleDoubleProperty yProperty() {
		return myYProperty;
	}
	
	protected SimpleDoubleProperty rotationProperty() {
		return myRotationProperty;
	}
	
	protected SimpleBooleanProperty penDownProperty() {
		return myPenDownProperty;
	}
	
	protected SimpleBooleanProperty isMovingProperty() {
		return isMovingProperty;
	}

	protected ImageView getView() {
		return myImageView;
	}
	
	protected TurtleDisplay getDisplay() {
		return myDisplay;
	}
	
	protected void setImage(String url) {
		myImageView.setImage(new Image(url));
		this.centerImage();
	}

	protected void setPenDown(boolean down) {
		myPenDownProperty.set(down);
	}

	protected void setPenColor(Color color) {
		myPenColor = color;
	}

	private void setPenWidth(double width) {
		myPenWidth = width;
	}

	protected void setRotation(double degrees) {
		myRotationProperty.set(degrees);
		myImageView.setRotate(degrees + 90);
	}
	
	protected void setDestination(Point destination, double speed) {
		isMovingProperty.set(true);
		double distX = destination.getX() - myXProperty.get();
		double distY = destination.getY() - myYProperty.get();
		double stepsToDestination = getNumStepsAlongPath(distX, distY, speed);
		
		myStepsRemaining = (int) (stepsToDestination);
		double myStepSizeX = distX / stepsToDestination;
		double myStepSizeY = distY / stepsToDestination;
		myStepSize = new Point(myStepSizeX, myStepSizeY);
		
		myDestination = destination;
	}

	protected void updateMovement() {
		if (myStepsRemaining > 0) {
			this.stepTowardsDestination();
			myStepsRemaining--;
		} else {
			isMovingProperty.set(false);
		}
	}

	protected void addFutureDestination(Point destination) {
		myFutureDestinations.add(destination);
		myDestination = destination;
	}

	protected boolean hasAnotherDestination() {
		return !myFutureDestinations.isEmpty();
	}

	protected Point pollFutureDestination() {
		return myFutureDestinations.poll();
	}

	private void setLocation(Point point) {
		myXProperty.set(point.getX());
		myYProperty.set(point.getY());
		this.centerImage();
	}

	private void centerImage() {
		Point adjusted = new Point(myXProperty.get(), myYProperty.get());
		if(!isInBounds(adjusted)) {
			adjusted = wrap(adjusted);
		}
		myImageView.setX(adjusted.getX() - myImageView.getBoundsInLocal().getWidth() / 2.0);
		myImageView.setY(adjusted.getY() - myImageView.getBoundsInLocal().getHeight() / 2.0);
	}

	private void stepTowardsDestination() {
		Point step = new Point(myXProperty.get() + myStepSize.getX(), myYProperty.get() + myStepSize.getY());

		if (myPenDownProperty.get()) {
			Point adjustedLoc = new Point(myXProperty.get(), myYProperty.get());
			Point adjustedStep = step;
			if(!isInBounds(adjustedLoc) && !isInBounds(adjustedStep)) {
				adjustedLoc = wrap(adjustedLoc);
				adjustedStep = wrap(adjustedStep);
			}
			if(areAdjacentPoints(adjustedLoc, adjustedStep)) {
				myDisplay.drawLine(adjustedLoc, adjustedStep, myPenColor, myPenWidth);
			}
		}

		this.setLocation(step);
	}

	private boolean isInBounds(Point point) {
		return (point.getX() >= (-myDisplay.getWidth() / 2.0) && point.getX() <= (myDisplay.getWidth() / 2.0)
				&& point.getY() >= (-myDisplay.getHeight() / 2.0) && point.getY() <= (myDisplay.getHeight() / 2.0));
	}

	private Point wrap(Point point) {
		double adjustedX = point.getX();
		double adjustedY = point.getY();
		if(myDisplay.getWidth() > 0 && myDisplay.getHeight() > 0) {
			double leftBoundary = -myDisplay.getWidth() / 2.0;
			double rightBoundary = myDisplay.getWidth() / 2.0;
			double lowerBoundary = -myDisplay.getHeight() / 2.0;
			double upperBoundary = myDisplay.getHeight() / 2.0;
	
			while(adjustedX >= rightBoundary) {
				adjustedX -= myDisplay.getWidth();
			}
			while(adjustedX < leftBoundary) {
				adjustedX += myDisplay.getWidth();
			}
			while(adjustedY >= upperBoundary) {
				adjustedY -= myDisplay.getHeight();
			}
			while(adjustedY < lowerBoundary) {
				adjustedY += myDisplay.getHeight();
			}
		}
		return new Point(adjustedX, adjustedY);
	}
	
	private boolean areAdjacentPoints(Point a, Point b) {
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
