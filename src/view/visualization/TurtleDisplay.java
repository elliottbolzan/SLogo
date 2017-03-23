package view.visualization;

import utils.Point;
import view.Workspace;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

/**
 * @author Jay Doherty
 * This class handles the part of the UI that displays turtles and animates their movements.
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
	
	public TurtleDisplay(Workspace workspace, int initialNumber, Image turtleImage, Color backgroundColor) {
		myLineLength = 1.0;
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myWorkspace = workspace;
	
		this.setMinSize(300, 280);
		this.setPrefSize(SIZE, SIZE);

		this.createDisplayArea(SIZE, SIZE);
		this.createToolBar(SIZE);
		this.setBackgroundColor(backgroundColor);

		turtleManager = new TurtleManager(initialNumber, this, turtleImage);

		animationIsPlaying = true;
	}

	protected Workspace getWorkspace() {
		return myWorkspace;
	}

	/**
	 * @return the display's turtle manager object
	 */
	public TurtleManager getTurtleManager() {
		return turtleManager;
	}

	/**
	 * Clears the display
	 */
	public void clear() {
		myDisplayArea.getChildren().clear();
		turtleManager.clear();
	}

	/**
	 * @return the display's background color
	 */
	public Color getBackgroundColor() {
		return (Color) myDisplayArea.getBackground().getFills().get(0).getFill();
	}

	/**
	 * @param color : the display's background color
	 */
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
	protected void moveTurtle(Turtle turtle, Point destination) {
		if (turtle.isMovingProperty().get()) {
			turtle.getSchedule().addFutureDestination(destination);
		} else {
			turtle.setDestination(destination, myLineLength);
		}

		if (myAnimationSpeed.get() == mySpeedSlider.getMax()) {
			while (turtle.isMovingProperty().get()) {
				turtle.updateMovement();
			}
		}
	}

	protected boolean isInBounds(Point point) {
		return (point.getX() >= (-myDisplayArea.getWidth() / 2.0) && point.getX() <= (myDisplayArea.getWidth() / 2.0)
				&& point.getY() >= (-myDisplayArea.getHeight() / 2.0) && point.getY() <= (myDisplayArea.getHeight() / 2.0));
	}
	
	protected Point wrapIntoView(Point point) {
		double adjustedX = point.getX();
		double adjustedY = point.getY();
		if (myDisplayArea.getWidth() > 0 && myDisplayArea.getHeight() > 0) {
			double leftBoundary = -myDisplayArea.getWidth() / 2.0;
			double rightBoundary = myDisplayArea.getWidth() / 2.0;
			double lowerBoundary = -myDisplayArea.getHeight() / 2.0;
			double upperBoundary = myDisplayArea.getHeight() / 2.0;

			while (adjustedX >= rightBoundary) {
				adjustedX -= myDisplayArea.getWidth();
			}
			while (adjustedX < leftBoundary) {
				adjustedX += myDisplayArea.getWidth();
			}
			while (adjustedY >= upperBoundary) {
				adjustedY -= myDisplayArea.getHeight();
			}
			while (adjustedY < lowerBoundary) {
				adjustedY += myDisplayArea.getHeight();
			}
		}
		return new Point(adjustedX, adjustedY);
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
		HBox myToolBar = new HBox();
		myAnimationSpeed = new SimpleDoubleProperty();

		mySpeedSlider = new Slider(0, 1, 0.2);
		mySpeedSlider.setPrefWidth(width / 4);
		mySpeedSlider.setShowTickMarks(false);
		mySpeedSlider.setMajorTickUnit(0.25);

		myAnimationSpeed.bind(mySpeedSlider.valueProperty());
		mySpeedSlider.valueProperty().addListener(e -> this.resetAnimation(myAnimationSpeed.get()));
		this.resetAnimation(myAnimationSpeed.get());
		myAnimation.play();

		Button playButton = new Button("Play");
		playButton.setOnAction(e -> this.playAnimation());
		Button stopButton = new Button("Stop");
		stopButton.setOnAction(e -> this.stopAnimation());
		Button stepButton = new Button("Step");
		stepButton.setOnAction(e -> this.playSingleAnimation());
		
		Label speedLabel = new Label("Speed:");
		speedLabel.setTextFill(Color.BLACK);
		speedLabel.setPadding(new Insets(0, 0, 0, 5));
		
		HBox.setHgrow(playButton, Priority.ALWAYS);
		HBox.setHgrow(stopButton, Priority.ALWAYS);
		HBox.setHgrow(stepButton, Priority.ALWAYS);
		HBox.setHgrow(mySpeedSlider, Priority.ALWAYS);
		playButton.setMaxWidth(Double.MAX_VALUE);
		stopButton.setMaxWidth(Double.MAX_VALUE);
		stepButton.setMaxWidth(Double.MAX_VALUE);
		mySpeedSlider.setMaxWidth(Double.MAX_VALUE);

		myToolBar.getChildren().addAll(playButton, stopButton, stepButton, speedLabel, mySpeedSlider);
		myToolBar.setPrefWidth(width / 2);
		myToolBar.setAlignment(Pos.BASELINE_CENTER);
		myToolBar.translateYProperty().bind(myDisplayArea.heightProperty().subtract(playButton.heightProperty()));
		
		this.getChildren().add(myToolBar);
	}
	
	private void resetAnimation(double speed) {
		double millisInterval = 1.0 / (0.01 + 4 * speed * speed);
		boolean animationWasGoing = myAnimation.getCurrentRate() > 0;
		myAnimation.stop();
		myAnimation.getKeyFrames().clear();
		KeyFrame frame = new KeyFrame(Duration.millis(millisInterval), e -> this.stepTurtles());
		myAnimation.getKeyFrames().add(frame);
		if (animationWasGoing) {
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

	private void stepTurtles() {
		turtleManager.stepTurtles();
	}
	
	private void playAnimation() {
		animationIsPlaying = true;
		myAnimation.play();
	}

	private void playSingleAnimation() {
		animationIsPlaying = false;
		myAnimation.play();
	}
}
