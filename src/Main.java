import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Elliott Bolzan
 *
 * This class is solely used to launch the application. 
 */
public class Main extends Application {
	
	/**
	 * Begins the launching process.
	 * @param args the arguments passed into main. 
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Creates an instance of the controller.
	 * @param primaryStage the application's primary stage / window.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		new Controller(primaryStage);
	}

}
