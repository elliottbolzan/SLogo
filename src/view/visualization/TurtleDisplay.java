package view.visualization;

import utils.Point;

import java.awt.Dimension;

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
public class TurtleDisplay extends Group {

	private Pane myDisplayArea;
	private Dimension myDimensions;

	private Turtle myTurtle;
	private double myLineLength;

	private Timeline myAnimation;

	public TurtleDisplay(int width, int height) {
		this.createDisplayArea(width, height);
		this.setBackgroundColor(Color.WHITE);
		this.createTurtle();
		myLineLength = 1.0;
	}

	public Turtle getTurtle() {
		return myTurtle;
	}

	public void clear() {
		myDisplayArea.getChildren().clear();
		this.createTurtle();
	}

	public double getWidth() {
		return myDisplayArea.getWidth();
	}

	public double getHeight() {
		return myDisplayArea.getHeight();
	}

	public Color getBackgroundColor() {
		return (Color) myDisplayArea.getBackground().getFills().get(0).getFill();
	}

	public Color getPenColor() {
		return myTurtle.getPenColor();
	}

	public Dimension getDimensions() {
		return myDimensions;
	}

	public void startAnimation() {
		myAnimation.play();
	}

	/**
	 * This method sets the destination of the turtle.
	 * 
	 * @param point
	 */
	public void moveTurtle(Point destination) {
		myTurtle.setDestination(destination, myLineLength);
		this.recalculateAnimationSpeed(destination);
		myAnimation.play();
	}

	public void turnTurtle(double degrees) {
		myTurtle.setRotation(myTurtle.getRotation() + degrees);
	}

	public void setPenDown(boolean down) {
		myTurtle.setPenDown(down);
	}

	public void setPenColor(Color color) {
		myTurtle.setPenColor(color);
	}

	public void setTurtleVisible(boolean visible) {
		myTurtle.setVisible(visible);
	}

	public void setTurtleImage(String url) {
		myTurtle.setImage(url);
	}

	public void drawLine(Point start, Point finish, Color color, double width) {
		Line line = new Line(start.getX(), start.getY(), finish.getX(), finish.getY());
		line.setStroke(color);
		line.setStrokeWidth(width);
		this.addToDisplayArea(line);
	}

	public void setBackgroundColor(Color color) {
		BackgroundFill primaryLayer = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(primaryLayer);
		myDisplayArea.setBackground(background);
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

		this.getChildren().addAll(myDisplayArea);
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

	private void createTurtle() {
		myTurtle = new Turtle(this);
		this.addToDisplayArea(myTurtle);
	}

	private void recalculateAnimationSpeed(Point destination) {
		double distance = this.distanceBetween(myTurtle.getLocation(), destination);
		double animationTickInterval = 1000.0 / distance;
		this.resetAnimation(animationTickInterval);
	}

	private double distanceBetween(Point a, Point b) {
		double distanceX = a.getX() - b.getX();
		double distanceY = a.getY() - b.getY();
		return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
	}

	private void resetAnimation(double millisInterval) {
		myAnimation = new Timeline();
		myAnimation.getKeyFrames().clear();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		KeyFrame frame = new KeyFrame(Duration.millis(millisInterval), e -> this.stepAnimation());
		myAnimation.getKeyFrames().add(frame);
	}

	private void stepAnimation() {
		if (myTurtle.isMoving()) {
			myTurtle.updateMovement();
		} else {
			myAnimation.pause();
		}
	}
}
