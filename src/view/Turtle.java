package view;

import utils.Point;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class Turtle extends Group {
	private static final String TURTLE_IMAGE = "view/turtle.png";
	private ImageView myImage;
	
	private TurtleDisplay myDisplay;
	
	private Point myDestination;
	private int myStepsRemaining;
	private double myStepX;
	private double myStepY;
	
	private Point myLocation;
	private double myRotation;
	private boolean myPenDown;
	
	public Turtle(TurtleDisplay home) {
		myImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE)));
		this.getChildren().add(myImage);
		
		myDisplay = home;
		
		myRotation = 0.0;
		myPenDown = true;
		this.setLocation(new Point(0,0));
	}
	
	protected Point getLocation() {
		return myLocation;
	}
	
	protected double getRotation() {
		return myRotation;
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
		myImage.setRotate(degrees);
	}
	
	protected void setPenDown(boolean down) {
		myPenDown = down;
	}
	
	protected void setDestination(Point point, double speed) {
		myDestination = point;
		
		double distX = myDestination.getX() - myLocation.getX();
		double distY = myDestination.getY() - myLocation.getY();
		double stepsToDestination; 
		
		if(Math.abs(distY) >= Math.abs(distX)) {
			stepsToDestination = Math.abs(distY/speed);
		} else {
			stepsToDestination = Math.abs(distX/speed);
		}
		
		myStepsRemaining = (int)(stepsToDestination);
		myStepY = distY / stepsToDestination;
		myStepX = distX / stepsToDestination;
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
		Point step = new Point(myLocation.getX() + myStepX, 
							   myLocation.getY() + myStepY);
		
		if(myPenDown) {
			Line line = new Line(myLocation.getX(), myLocation.getY(), step.getX(), step.getY());
			myDisplay.getChildren().add(line);
		}
				
		this.setLocation(step);
	}
	
	private boolean isInBounds(Point point) {
		return (point.getX() >= 0 && point.getX() < myDisplay.getWidth() && 
				point.getY() >= 0 && point.getY() < myDisplay.getHeight());
	}
	
	private Point wrap(Point point) {
		Point result = new Point(point);
		if(point.getX() < 0) {
			result.setX(myDisplay.getWidth());
		} else if (point.getX() >= myDisplay.getWidth()) {
			result.setX(0);
		}
		
		if(point.getY() < 0) {
			result.setY(myDisplay.getHeight());
		} else if(point.getY() >= myDisplay.getHeight()) {
			result.setY(0);
		}
		return result;
	}
}
