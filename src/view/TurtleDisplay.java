package view;

import utils.Point;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;

/**
 * @author Jay Doherty
 *
 */
public class TurtleDisplay extends Group {
	private Pane myDisplayArea;
	private Turtle myTurtle;
	private double myTurtleSpeed;

	public TurtleDisplay(int width, int height) {
		this.createDisplayArea(width, height);
		this.createTurtle();
		myTurtleSpeed = 1.0;
		myTurtle.setLocation(new Point(width/2, height/2));
		this.setBackgroundColor(Color.ALICEBLUE); //TODO: remove this later, just helps to see placement
	}
	
	protected Turtle getTurtle() {
		return myTurtle;
	}
	
	protected void clear() {
		myDisplayArea.getChildren().clear();
		this.createTurtle();
	}
	
	protected double getWidth() {
		return myDisplayArea.getWidth();
	}
	
	protected double getHeight() {
		return myDisplayArea.getHeight();
	}
	
	/**
	 * This method works by braking the path into steps and moving the turtle one
	 * step at a time.
	 * @param point
	 */
	protected void moveTurtle(Point point) {
		myTurtle.setDestination(point, myTurtleSpeed);
		
		while(myTurtle.isMoving()) {
			myTurtle.updateMovement();
		}
	}
	
	protected void turnTurtle(double degrees) {
		myTurtle.setRotation(myTurtle.getRotation() + degrees);
	}
	
	protected void setPenDown(boolean down) {
		myTurtle.setPenDown(down);
	}
	
	protected void setTurtleVisible(boolean visible) {
		myTurtle.setVisible(visible);
	}
	
	protected void setBackgroundColor(Paint paint) {
		BackgroundFill primaryLayer = new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(primaryLayer);
		myDisplayArea.setBackground(background);
	}
	
	private void createDisplayArea(int width, int height) {
		myDisplayArea = new Pane();
		myDisplayArea.setPrefSize(width, height);
		
		Rectangle clipBoundaries = new Rectangle();
		clipBoundaries.widthProperty().bind(myDisplayArea.widthProperty());
		clipBoundaries.heightProperty().bind(myDisplayArea.heightProperty());
		myDisplayArea.setClip(clipBoundaries);
				
		this.getChildren().addAll(myDisplayArea);		
	}
	
	private void createTurtle() {
		myTurtle = new Turtle(this);
		myDisplayArea.getChildren().add(myTurtle);
	}
}
