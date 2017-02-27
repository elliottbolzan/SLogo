package view;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Elliott Bolzan
 *
 */
public class TurtleWindow extends Stage {
	
	private TurtleDisplay turtleDisplay;

	/**
	 * 
	 */
	public TurtleWindow() {
		turtleDisplay = new TurtleDisplay(500, 500);
		setTitle("Turtle Display");
		setResizable(false);
		setScene(new Scene(turtleDisplay));
		setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				System.exit(0);
			}
		});
	}
	
	public TurtleDisplay getDisplay() {
		return turtleDisplay;
	}

}
