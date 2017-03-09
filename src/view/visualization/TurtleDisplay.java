package view.visualization;

import utils.Point;
import view.Workspace;

import java.awt.Dimension;
import java.util.Collections;
import java.util.HashMap;
import java.util.Collection;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @author Jay Doherty
 *
 */
public class TurtleDisplay extends StackPane {
	
	private final static int SIZE = 450;

	private Workspace myWorkspace;
	
	private Pane myDisplayArea;
	private HBox myToolBar;
	private Dimension myDimensions;

	private Map<Integer, Turtle> myTurtles;
	private double myLineLength;

	private Timeline myAnimation;
	private boolean isAnimated;
	private SimpleDoubleProperty myAnimationSpeed;

	public TurtleDisplay(Workspace workspace) {
		myTurtles = new HashMap<Integer, Turtle>();
		myLineLength = 1.0;
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		
		myWorkspace = workspace;
		
		this.setMinSize(300, 280);
		this.setPrefSize(SIZE, SIZE);
		
		this.createDisplayArea(SIZE, SIZE);
		this.createToolBar(SIZE);
		this.setBackgroundColor(Color.WHITE);
		this.createTurtle(1);

		isAnimated = true;
	}

	protected Workspace getWorkspace() {
		return myWorkspace;
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
		}

		if (!isAnimated) {
			while (turtle.isMovingProperty().get()) {
				turtle.updateMovement();
			}
		}
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
		
		this.getChildren().add(myDisplayArea);
	}
	
	private void createToolBar(int width) {
		myToolBar = new HBox();
		myToolBar.setPrefWidth(width);
		myAnimationSpeed = new SimpleDoubleProperty();
		
		Slider speedSlide = new Slider(0,1,0.2);
		speedSlide.setPrefWidth(width/2);
		speedSlide.setShowTickMarks(true);
		speedSlide.setShowTickLabels(true);
		speedSlide.setMajorTickUnit(0.25);
		
		myAnimationSpeed.bind(speedSlide.valueProperty());
		speedSlide.valueProperty().addListener(e -> this.resetAnimation(myAnimationSpeed.get()));	
		this.resetAnimation(myAnimationSpeed.get());
		
		myToolBar.getChildren().add(speedSlide);
		this.getChildren().add(myToolBar);
	}

	private void createTurtle(int id) {
		Turtle turtle = new Turtle(id, this);
		myTurtles.put(id, turtle);
		this.addToDisplayArea(turtle.getView());
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
	
	private void resetAnimation(double speed) {
		double millisInterval = 1.0/(0.01 + 4*speed*speed);
		myAnimation.stop();
		myAnimation.getKeyFrames().clear();
		KeyFrame frame = new KeyFrame(Duration.millis(millisInterval), e -> this.stepAnimation());
		myAnimation.getKeyFrames().add(frame);
		myAnimation.play();
	}
}
