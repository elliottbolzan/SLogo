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
import javafx.scene.layout.StackPane;

/**
 * @author Jay Doherty
 *
 */
public class TurtleDisplay extends StackPane {
	
	private final static int SIZE = 450;

	private Pane myDisplayArea;
	private Dimension myDimensions;

	private Map<Integer, Turtle> myTurtles;
	private double myLineLength;

	private Timeline myAnimation;
	private boolean isAnimated;

	public TurtleDisplay() {
		setMinSize(300, 280);
		this.createDisplayArea(SIZE, SIZE);
		setPrefSize(SIZE, SIZE);
		this.setBackgroundColor(Color.WHITE);
		myTurtles = new HashMap<Integer, Turtle>();
		
		this.createTurtle(1);
		this.createTurtleInfo();
		myLineLength = 1.0;
		
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Timeline.INDEFINITE);

		isAnimated = true;
	}

	public Dimension getDimensions() {
		return myDimensions;
	}
	
	public void clear() {
		myDisplayArea.getChildren().clear();
		this.createTurtle(1);
		this.createTurtleInfo();
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
		this.getTurtle(id).setRotation(this.getTurtle(id).getRotation() + degrees);
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

	protected void drawLine(Point start, Point finish, Color color, double width) {
		Line line = new Line(start.getX(), start.getY(), finish.getX(), finish.getY());
		line.setStroke(color);
		line.setStrokeWidth(width);
		this.addToDisplayArea(line);
	}
	
	private void createDisplayArea(int width, int height) {
		myDisplayArea = new Pane();
		myDisplayArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		myDisplayArea.setScaleY(-1.0);
		myDimensions = new Dimension(width, height);

		Rectangle clipBoundaries = new Rectangle();
		clipBoundaries.widthProperty().bind(myDisplayArea.widthProperty());
		clipBoundaries.heightProperty().bind(myDisplayArea.heightProperty());
		myDisplayArea.setClip(clipBoundaries);
		
		getChildren().add(myDisplayArea);
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
	
	private void createTurtleInfo() {
		TurtleInfo turtleInfo = new TurtleInfo(this, 1); 	//TODO
		turtleInfo.getView().setScaleY(-1);
		myDisplayArea.getChildren().add(turtleInfo.getView());
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
		myAnimation.stop();
		myAnimation.getKeyFrames().clear();
		KeyFrame frame = new KeyFrame(Duration.millis(millisInterval), e -> this.stepAnimation());
		myAnimation.getKeyFrames().add(frame);
		myAnimation.play();
	}

	private void recalculateAnimationSpeed(int id, Point destination) {
		double distance = this.distanceBetween(this.getTurtle(id).getCurrentLocation(), destination);
		double animationTickInterval = 1000.0 / distance;
		this.resetAnimation(animationTickInterval);
	}
	
	private double distanceBetween(Point a, Point b) {
		double distanceX = a.getX() - b.getX();
		double distanceY = a.getY() - b.getY();
		return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
	}
}
