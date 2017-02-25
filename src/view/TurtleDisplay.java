package view;

import utils.Point;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
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
	private double myLineLength;
	
	private Timeline myAnimation;
	private boolean isAnimated;
	private double myAnimationTickInterval;

	public TurtleDisplay(int width, int height) {
		this.createDisplayArea(width, height);
		this.setBackgroundColor(Color.ALICEBLUE);
		this.createTurtle();
		myLineLength = 1.0;
		
		isAnimated = false;
		myAnimationTickInterval = 25.0;
		this.resetAnimation(myAnimationTickInterval);
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
	
	protected void startAnimation() {
		isAnimated = true;
		myAnimation.play();
	}
	
	/**
	 * This method sets the destination of the turtle.
	 * @param point
	 */
	protected void moveTurtle(Point destination) {
		myTurtle.setDestination(destination, myLineLength);
		
		if(!isAnimated) {
			this.rushTurtleToDestination();
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
	
	protected void drawLine(Point start, Point finish) {
		Line line = new Line(start.getX(), start.getY(), finish.getX(), finish.getY());
		this.addToDisplayArea(line);
	}
	
	protected void setBackgroundColor(Paint paint) {
		BackgroundFill primaryLayer = new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(primaryLayer);
		myDisplayArea.setBackground(background);
	}
	
	private void createDisplayArea(int width, int height) {
		myDisplayArea = new Pane();
		myDisplayArea.setPrefSize(width, height);
		myDisplayArea.setScaleY(-1.0);
		
		Rectangle clipBoundaries = new Rectangle();
		clipBoundaries.widthProperty().bind(myDisplayArea.widthProperty());
		clipBoundaries.heightProperty().bind(myDisplayArea.heightProperty());
		myDisplayArea.setClip(clipBoundaries);
				
		this.getChildren().addAll(myDisplayArea);		
	}
	
	private void addToDisplayArea(Node element) {
		element.setLayoutX(myDisplayArea.getWidth()/2.0);
		element.setLayoutY(myDisplayArea.getHeight()/2.0);
		myDisplayArea.widthProperty().addListener(e -> {element.setLayoutX(myDisplayArea.getWidth()/2.0);});
		myDisplayArea.heightProperty().addListener(e -> {element.setLayoutY(myDisplayArea.getHeight()/2.0);});
		myDisplayArea.getChildren().add(element);
	}
	
	private void createTurtle() {
		myTurtle = new Turtle(this);
		this.addToDisplayArea(myTurtle);
	}
	
	private void resetAnimation(double millisInterval) {
		myAnimation = new Timeline();
		myAnimation.getKeyFrames().clear();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		KeyFrame frame = new KeyFrame(Duration.millis(millisInterval), e->this.stepAnimation());
		myAnimation.getKeyFrames().add(frame);
	}
	
	private void stepAnimation() {
		if(myTurtle.isMoving()) {
			myTurtle.updateMovement();
		}
	}
	
	private void rushTurtleToDestination() {
		while(myTurtle.isMoving()) {
			myTurtle.updateMovement();
		}
	}
}
