package view.visualization;

import utils.Point;
import view.Workspace;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
	private Slider mySpeedSlider;

	private TurtleManager turtleManager;
	private double myLineLength;

	private Timeline myAnimation;
	private boolean animationIsPlaying;
	private SimpleDoubleProperty myAnimationSpeed;

	public TurtleDisplay(Workspace workspace, int initialNumber) {
		myLineLength = 1.0;
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myWorkspace = workspace;

		this.setMinSize(300, 280);
		this.setPrefSize(SIZE, SIZE);

		this.createDisplayArea(SIZE, SIZE);
		this.createToolBar(SIZE);
		this.setBackgroundColor(Color.WHITE);

		turtleManager = new TurtleManager(initialNumber, this);

		animationIsPlaying = true;
	}
	
	protected Workspace getWorkspace() {
		return myWorkspace;
	}

	public TurtleManager getTurtleManager() {
		return turtleManager;
	}

	public void clear() {
		myDisplayArea.getChildren().clear();
		turtleManager.clear();
	}

	public Color getBackgroundColor() {
		return (Color) myDisplayArea.getBackground().getFills().get(0).getFill();
	}

	public void setBackgroundColor(Color color) {
		BackgroundFill primaryLayer = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(primaryLayer);
		myDisplayArea.setBackground(background);
	}

	/**
	 * This method sets the destination of the turtle to a point, unless the
	 * turtle is already moving in which case it gets queued for later
	 * execution. If animation is off, this method also sends the turtle to its
	 * destination.
	 * 
	 * @param point
	 */
	public void moveTurtle(Turtle turtle, Point destination) {
		if (turtle.isMovingProperty().get()) {
			turtle.addFutureDestination(destination);
		} else {
			turtle.setDestination(destination, myLineLength);
		}
		
		if (myAnimationSpeed.get() == mySpeedSlider.getMax()) {
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

		Rectangle clipBoundaries = new Rectangle();
		clipBoundaries.widthProperty().bind(myDisplayArea.widthProperty());
		clipBoundaries.heightProperty().bind(myDisplayArea.heightProperty());
		myDisplayArea.setClip(clipBoundaries);

		this.getChildren().add(myDisplayArea);
	}

	private void createToolBar(int width) {
		HBox myToolBar = new HBox(5);
		myAnimationSpeed = new SimpleDoubleProperty();

		mySpeedSlider = new Slider(0, 1, 0.2);
		mySpeedSlider.setPrefWidth(width / 2);
		mySpeedSlider.setShowTickMarks(true);
		mySpeedSlider.setMajorTickUnit(0.25);

		myAnimationSpeed.bind(mySpeedSlider.valueProperty());
		mySpeedSlider.valueProperty().addListener(e -> this.resetAnimation(myAnimationSpeed.get()));
		this.resetAnimation(myAnimationSpeed.get());

		Button playButton = new Button("Play");
		playButton.setOnAction(e -> this.playAnimation());
		Button stopButton = new Button("Stop");
		stopButton.setOnAction(e -> this.stopAnimation());
		Button stepButton = new Button("Step");
		stepButton.setOnAction(e -> this.singleStepCommand());
		
		myToolBar.getChildren().addAll(playButton,stopButton,stepButton, mySpeedSlider);
		myToolBar.setPrefWidth(width);
		myToolBar.translateYProperty().bind(myDisplayArea.heightProperty().subtract(playButton.heightProperty()));
		this.getChildren().add(myToolBar);
	}

	protected void addToDisplayArea(Node element) {
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
		turtleManager.stepTurtles();
	}

	private void resetAnimation(double speed) {
		double millisInterval = 1.0 / (0.01 + 4 * speed * speed);
		boolean animationWasGoing = myAnimation.getRate() > 0;
		myAnimation.stop();
		myAnimation.getKeyFrames().clear();
		KeyFrame frame = new KeyFrame(Duration.millis(millisInterval), e -> this.stepAnimation());
		myAnimation.getKeyFrames().add(frame);
		if(animationWasGoing) {
			myAnimation.play();
		}
	}
	
	protected boolean animationIsPlaying() {
		return animationIsPlaying;
	}
	
	protected void stopAnimation() {
		animationIsPlaying = false;
		myAnimation.stop();
	}
	
	private void playAnimation() {
		animationIsPlaying = true;
		myAnimation.play();
	}
	
	private void singleStepCommand() {
		animationIsPlaying = false;
		myAnimation.play();
	}
}
