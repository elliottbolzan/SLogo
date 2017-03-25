package view.components;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import utils.Direction;

/**
 * @author Jay Doherty
 * This class makes four buttons for controlling the turtles (forward, back, left, right)
 */
public class TurtleButtons {

	private static final int INCREMENT = 10;
	
	private HBox myBar;
	private Controller myController;
	
	public TurtleButtons(Controller control) {
		myBar = new HBox();
		myController = control;
		
		Button leftButton = new Button("Left");
		leftButton.setOnAction(e -> this.handle(Direction.LEFT));
		Button rightButton = new Button("Right");
		rightButton.setOnAction(e -> this.handle(Direction.RIGHT));
		Button forwardButton = new Button("Forward");
		forwardButton.setOnAction(e -> this.handle(Direction.FORWARD));
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> this.handle(Direction.BACK));
		
		myBar.getChildren().addAll(leftButton, forwardButton, backButton, rightButton);
		myBar.setPadding(new Insets(0, 0, 10, 0));
	}
	
	/**
	 * @return the JavaFX element containing all of the buttons
	 */
	public HBox getView() {
		return myBar;
	}

	private void handle(Direction direction) {
		String result = direction.name().toLowerCase() + " " + String.valueOf(INCREMENT);
		myController.parse(result, false);
	}
}
