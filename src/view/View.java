package view;

import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Model;

/**
 * @author Elliott Bolzan
 *
 * This class serves as the view component of the application.
 * It initializes and controls the user interface.
 */
public class View implements ViewAPI {
	
	private Stage stage;
	private Model model;

	/**
	 * Creates a View object.
	 * Initializes an instance of the Model.
	 * @param stage the program's primary window.
	 * @return a View object.
	 */
	public View(Stage stage) {
		this.stage = stage;
		model = new Model(this);
		setupStage();
	}
	

	/**
	 * Initializes the stage, by setting its title and properties.
	 * The application will quit when the main window is closed.
	 */
	private void setupStage() {
		stage.setTitle("SLogo Interpreter");
		stage.setResizable(false);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				System.exit(0);
			}
		});
		stage.show();
	}

}
