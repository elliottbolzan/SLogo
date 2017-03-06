package view.visualization;

import utils.Point;

import java.awt.Dimension;
import java.util.Collections;
import java.util.HashMap;
import java.util.Collection;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
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
public class TurtleDisplay {

	private Pane myDisplayArea;
	private Dimension myDimensions;

	private Map<Integer, Turtle> myTurtles;
	private double myLineLength;

	private Timeline myAnimation;
	private boolean isAnimated;

	public TurtleDisplay(int width, int height) {
		this.createDisplayArea(width, height);
		this.setBackgroundColor(Color.WHITE);
		myTurtles = new HashMap<Integer, Turtle>();
		this.createTurtle(1);
		myLineLength = 1.0;
		isAnimated = false;
	}

	public Dimension getDimensions() {
		return myDimensions;
	}
	
	public void clear() {
		myDisplayArea.getChildren().clear();
		this.createTurtle(1);
	}
	
	public Color getBackgroundColor() {
		return (Color) myDisplayArea.getBackground().getFills().get(0).getFill();
	}

	public void setBackgroundColor(Color color) {
		BackgroundFill primaryLayer = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(primaryLayer);
		myDisplayArea.setBackground(background);
	}
	
	public Turtle getTurtle(int id) {
		if(!myTurtles.containsKey(id)) {
			this.createTurtle(id);
		}
		return myTurtles.get(id);
	}
	
	public Collection<Turtle> getAllTurtles() {
		return Collections.unmodifiableCollection(myTurtles.values());
	}
	
	public void setPenDown(int id, boolean down) {
		this.getTurtle(id).setPenDown(down);
	}

	public void setPenColor(int id, Color color) {
		this.getTurtle(id).setPenColor(color);
	}
	
	public void setTurtleVisible(int id, boolean visible) {
		this.getTurtle(id).getView().setVisible(visible);
	}

	public void setTurtleImage(int id, String url) {
		this.getTurtle(id).setImage(url);
	}

	public void turnTurtle(int id, double degrees) {
		//TODO do animation
		this.getTurtle(id).setRotation(myTurtles.get(id).getRotation() + degrees);
	}
	
	/**
	 * This method sets the destination of the turtle to a point, unless the turtle
	 * is already moving in which case it gets queued for later execution. If 
	 * animation is off, this method also sends the turtle to its destination.
	 * @param point
	 */
	public void moveTurtle(int id, Point destination) {
		Turtle turtle = this.getTurtle(id);
		
		if (turtle.isMovingProperty().get()) {
			turtle.addFutureDestination(destination);
		} else {
			turtle.setDestination(destination, myLineLength);
			this.recalculateAnimationSpeed(id, destination);
			myAnimation.play();
		}

		if (!isAnimated) {
			while (turtle.isMovingProperty().get()) {
				turtle.updateMovement();
			}
		}
	}

	protected Group getView() {
		return new Group(myDisplayArea);
	}
	
	protected double getWidth() {
		return myDisplayArea.getWidth();
	}

	protected double getHeight() {
		return myDisplayArea.getHeight();
	}
	
	protected void drawLine(Point start, Point finish, Color color, double width) {
		Line line = new Line(start.getX(), start.getY(), finish.getX(), finish.getY());
		line.setStroke(color);
		line.setStrokeWidth(width);
		this.addToDisplayArea(line);
	}
	
	private void createDisplayArea(int width, int height) {
		myDisplayArea = new Pane();
		myDisplayArea.setPrefSize(width, height);
		myDisplayArea.setScaleY(-1.0);
		myDimensions = new Dimension(width, height);

		Rectangle clipBoundaries = new Rectangle();
		clipBoundaries.widthProperty().bind(myDisplayArea.widthProperty());
		clipBoundaries.heightProperty().bind(myDisplayArea.heightProperty());
		myDisplayArea.setClip(clipBoundaries);
	}

	private void addToDisplayArea(Node element) {
		element.setLayoutX(myDisplayArea.getWidth() / 2.0);
		element.setLayoutY(myDisplayArea.getHeight() / 2.0);
		myDisplayArea.widthProperty().addListener(e -> {
			element.setLayoutX(myDisplayArea.getWidth() / 2.0);
		});
		myDisplayArea.heightProperty().addListener(e -> {
			element.setLayoutY(myDisplayArea.getHeight() / 2.0);
		});
		myDisplayArea.getChildren().add(element);
	}

	private void createTurtle(int id) {
		Turtle turtle = new Turtle(id, this);
		myTurtles.put(id, turtle);
		this.addToDisplayArea(turtle.getView());
	}

	private void stepAnimation() {
		for(Turtle turtle : myTurtles.values()) {
			if (turtle.isMovingProperty().get()) {
				turtle.updateMovement();
			} else {
				if (turtle.hasAnotherDestination()) {
					this.moveTurtle(turtle.getID(), turtle.pollFutureDestination());
				}
			}
		}
	}
	
	private void resetAnimation(double millisInterval) {
		myAnimation = new Timeline();
		myAnimation.getKeyFrames().clear();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		KeyFrame frame = new KeyFrame(Duration.millis(millisInterval), e -> this.stepAnimation());
		myAnimation.getKeyFrames().add(frame);
	}

	private void recalculateAnimationSpeed(int id, Point destination) {
		double distance = this.distanceBetween(myTurtles.get(id).getCurrentLocation(), destination);
		double animationTickInterval = 1000.0 / distance;
		this.resetAnimation(animationTickInterval);
	}
	
	private double distanceBetween(Point a, Point b) {
		double distanceX = a.getX() - b.getX();
		double distanceY = a.getY() - b.getY();
		return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
	}
}
