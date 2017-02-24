package view;

import java.awt.Dimension;
import java.awt.Point;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Elliott Bolzan
 *
 * This class serves as the view component of the application.
 * It initializes and controls the user interface.
 */
public class View implements ViewAPI {
	
	private Controller controller;
	private Stage stage;
	private Console console;
	private Panel panel;
	private TurtleDisplay turtleDisplay;

	/**
	 * Creates a View object.
	 * @param stage the program's primary window.
	 * @return a View object.
	 */
	public View(Controller controller, Stage stage) {
		this.controller = controller;
		this.stage = stage;
		console = new Console(this);
		panel  = new Panel(this);
		turtleDisplay = new TurtleDisplay();
		setup();
	}
	
	private void setup() {
		BorderPane pane = new BorderPane();
		pane.setLeft(panel);
		pane.setCenter(console);
		pane.setRight(turtleDisplay); //TODO: figure out where this should be in the scene
		setupStage(pane);
	}
	
	protected Stage getStage() {
		return stage;
	}
	
	protected Console getConsole() {
		return console;
	}
	
	protected Controller getController() {
		return controller;
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
		Scene scene = new Scene(pane, 1100, 400);
		scene.getStylesheets().add("view/style.css");
		console.focus();
		stage.setScene(scene);
		stage.show();
		
		turtleDisplay.moveTurtle(new Point(600, 100));
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
		turtleDisplay.moveTurtle(point);
	}


	@Override
	public void turn(double degrees) {
		turtleDisplay.turnTurtle(degrees);
	}


	@Override
	public void setPenDown(boolean down) {
		turtleDisplay.setPenDown(down);
	}


	@Override
	public void setTurtleVisible(boolean visible) {
		turtleDisplay.setTurtleVisible(visible);
	}


	@Override
	public void clearDisplay() {
		turtleDisplay.clear();
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
	
	protected void showMessage(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("SLogo encountered an error.");
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	protected void showHelp() {
		HelpView helpView = new HelpView();
		helpView.show();
	}

}
