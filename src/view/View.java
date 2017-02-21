package view;

import java.awt.Dimension;
import java.awt.Point;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
	private Console console;

	/**
	 * Creates a View object.
	 * Initializes an instance of the Model.
	 * @param stage the program's primary window.
	 * @return a View object.
	 */
	public View(Stage stage) {
		this.stage = stage;
		model = new Model(this);
		console = new Console();
		setup();
	}
	
	private void setup() {
		BorderPane pane = new BorderPane();
		pane.setCenter(console);
		setupStage(pane);
	}
	

	/**
	 * Initializes the stage, by setting its title and properties.
	 * The application will quit when the main window is closed.
	 */
	private void setupStage(BorderPane pane) {
		stage.setTitle("SLogo Interpreter");
		stage.setResizable(false);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				System.exit(0);
			}
		});
		Scene scene = new Scene(pane, 400, 400);
		scene.getStylesheets().add("view/style.css");
		stage.setScene(scene);
		stage.show();
	}


	@Override
	public void print(String string) {
		console.print(string);
	}


	@Override
	public void clearConsole() {
		console.clear();
	}


	@Override
	public void moveTo(Point point) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void turn(double degrees) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setPenDown(boolean down) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setTurtleVisible(boolean visible) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void clearDisplay() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Dimension getDisplaySize() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isPointInBounds(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

}
