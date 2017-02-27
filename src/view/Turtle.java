package view;

import utils.Point;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Turtle extends Group {
	private static final String TURTLE_IMAGE = "view/turtle.png";
	private ImageView myImage;
	
	private TurtleDisplay myDisplay;
	
	private Point myLocation;
	private double myRotation;
	
	private boolean myPenDown;
	private Color myPenColor;
	private double myPenWidth;
	
	private int myStepsRemaining;
	private Point myStepSize;
	
	public Turtle(TurtleDisplay home) {
		myImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE)));
		this.getChildren().add(myImage);
		
		myDisplay = home;
		
		myPenDown = true;
		myPenColor = Color.BLACK;
		myPenWidth = 1.0;
		
		this.setLocation(new Point(0,0));
		this.setRotation(90.0);
	}
	
	public Point getLocation() {
		return myLocation;
	}
	
	public double getRotation() {
		return myRotation;
	}
	
	protected Color getPenColor() {
		return myPenColor;
	}
	
	protected boolean isMoving() {
		return (myStepsRemaining > 0);
	}
	
	protected void setLocation(Point point) {
		myLocation = point;
		myImage.setX(point.getX() - myImage.getBoundsInLocal().getWidth()/2.0);
		myImage.setY(point.getY() - myImage.getBoundsInLocal().getHeight()/2.0);
	}
	
	protected void setRotation(double degrees) {
		myRotation = degrees;
		myImage.setRotate(degrees + 90);
	}
	
	protected void setPenDown(boolean down) {
		myPenDown = down;
	}
	
	protected void setPenColor(Color color) {
		myPenColor = color;
	}
	
	protected void setPenWidth(double width) {
		myPenWidth = width;
	}
	
	protected void setDestination(Point destination, double speed) {		
		double distX = destination.getX() - myLocation.getX();
		double distY = destination.getY() - myLocation.getY();
		double stepsToDestination = getNumStepsAlongPath(distX, distY, speed);
		
		myStepsRemaining = (int)(stepsToDestination);
		double myStepSizeX = distX / stepsToDestination;
		double myStepSizeY = distY / stepsToDestination;
		myStepSize = new Point(myStepSizeX, myStepSizeY);
	}
	
	protected void updateMovement() {
		if(myStepsRemaining > 0) {
			this.stepTowardsDestination();
			myStepsRemaining--;
		}
		
		if(!isInBounds(myLocation)) {
			this.setLocation(this.wrap(myLocation));
		}
	}
	
	private void stepTowardsDestination() {
		Point step = new Point(myLocation.getX() + myStepSize.getX(), 
							   myLocation.getY() + myStepSize.getY());
		
		if(myPenDown) {
			myDisplay.drawLine(myLocation, step, myPenColor, myPenWidth);
		}
				
		this.setLocation(step);
	}
	
	private boolean isInBounds(Point point) {
		return (point.getX() >= (-myDisplay.getWidth()/2.0) && point.getX() < (myDisplay.getWidth()/2.0) && 
				point.getY() >= (-myDisplay.getHeight()/2.0) && point.getY() < (myDisplay.getHeight()/2.0));
	}
	
	private Point wrap(Point point) {
		Point result = new Point(point);
		double leftBoundary = -myDisplay.getWidth()/2.0;
		double rightBoundary = myDisplay.getWidth()/2.0;
		double lowerBoundary = -myDisplay.getHeight()/2.0;
		double upperBoundary = myDisplay.getHeight()/2.0;
		
		if(point.getX() < leftBoundary) {
			result.setX(rightBoundary - 1);
		} else if (point.getX() >= rightBoundary) {
			result.setX(leftBoundary);
		}
		
		if(point.getY() < lowerBoundary) {
			result.setY(upperBoundary - 1);
		} else if(point.getY() >= upperBoundary) {
			result.setY(lowerBoundary);
		}
		return result;
	}
	
	private double getNumStepsAlongPath(double distanceX, double distanceY, double stepLength) {
		if(Math.abs(distanceY) >= Math.abs(distanceX)) {
			return Math.abs(distanceY/stepLength);
		} else {
			return Math.abs(distanceX/stepLength);
		}
	}
	
	public final class ImmutableTurtle {
		//TODO: implement this so getTurtle() is less exposing
	}
}
