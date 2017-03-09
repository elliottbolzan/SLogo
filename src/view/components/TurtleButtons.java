package view.components;

import controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;

/**
 * @author jaydoherty
 *
 */
public class TurtleButtons {

	private static final int INCREMENT = 10;
	
	private enum ButtonType {
		LEFT, RIGHT, FORWARD, BACK
	}
	
	private HBox myBar;
	private Controller myController;
	
	public TurtleButtons(Controller control) {
		myBar = new HBox();
		myController = control;
		
		Button leftButton = new Button("Left");
		leftButton.setOnAction(e -> this.handle(ButtonType.LEFT));
		Button rightButton = new Button("Right");
		rightButton.setOnAction(e -> this.handle(ButtonType.RIGHT));
		Button forwardButton = new Button("Forward");
		forwardButton.setOnAction(e -> this.handle(ButtonType.FORWARD));
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> this.handle(ButtonType.BACK));
		
		myBar.getChildren().addAll(leftButton, forwardButton, backButton, rightButton);
	}
	
	public HBox getView() {
		return myBar;
	}

	private void handle(ButtonType type) {
		String result = "";
		if (type == ButtonType.LEFT) {
			result += "left";
		} else if (type == ButtonType.RIGHT) {
			result += "right";
		} else if (type == ButtonType.FORWARD) {
			result += "forward";
		} else if (type == ButtonType.BACK) {
			result += "back";
		}
		if (!(result.equals(""))) {
			result += " " + String.valueOf(INCREMENT);
		}
		myController.parse(result, false);
	}
}
