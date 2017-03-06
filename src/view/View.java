package view;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Queue;

import utils.BadInputException;
import utils.Point;
import view.console.Console;
import view.panel.Panel;
import view.settings.HelpView;
import view.settings.SettingsView;
import view.visualization.Turtle;
import view.visualization.TurtleWindow;
import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.commands.Command;

/**
 * @author Elliott Bolzan
 *
 * This class serves as the view component of the application.
 * It initializes and controls the user interface.
 */
public class View implements ViewAPI {
	
	private Controller controller;
	private Stage stage;
	private String stylesheetPath;
	private Console console;
	private Panel panel;
	private TurtleWindow turtleWindow;

	/**
	 * Creates a View object.
	 * @param stage the program's primary window.
	 * @return a View object.
	 */
	public View(Controller controller, Stage stage, String stylesheetPath) {
		this.controller = controller;
		this.stage = stage;
		this.stylesheetPath = stylesheetPath;
		console = new Console(this);
		panel = new Panel(this);
		turtleWindow = new TurtleWindow(controller);
		setup();
	}
	
	private void setup() {
		BorderPane pane = new BorderPane();
		pane.setLeft(panel);
		pane.setCenter(console);
		setupStage(pane);
	}
	
	public Console getConsole() {
		return console;
	}
	
	public Controller getController() {
		return controller;
	}
	
	public String getStylesheetPath() {
		return stylesheetPath;
	}

	/**
	 * Initializes the stage, by setting its title and properties.
	 * The application will quit when the main window is closed.
	 */
	private void setupStage(BorderPane pane) {
		stage.setTitle(controller.getResources().getString("InterpreterTitle"));
		stage.setResizable(false);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				System.exit(0);
			}
		});
		Scene scene = new Scene(pane, 700, 400);
		scene.getStylesheets().add(stylesheetPath);
		console.focus();
		stage.setScene(scene);
		stage.setX(200);
		stage.show();
		turtleWindow.setY(stage.getY());
		turtleWindow.setX(stage.getX() + stage.getWidth() + 20);
		turtleWindow.show();
		stage.requestFocus();
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
		//TODO require turtle id
		turtleWindow.getDisplay().moveTurtle(1, point);
	}


	@Override
	public void turn(double degrees) {
		//TODO require turtle id
		turtleWindow.getDisplay().turnTurtle(1, degrees);
	}


	@Override
	public void setPenDown(boolean down) {
		//TODO require turtle id
		turtleWindow.getDisplay().setPenDown(1, down);
	}


	@Override
	public void setTurtleVisible(boolean visible) {
		//TODO require turtle id
		turtleWindow.getDisplay().setTurtleVisible(1, visible);
	}


	@Override
	public void clearDisplay() {
		turtleWindow.getDisplay().clear();
	}


	@Override
	public Dimension getDisplaySize() {
		return turtleWindow.getDisplay().getDimensions();
	}

	
	@Override
	public Turtle getTurtle() {
		//TODO require turtle id
		return turtleWindow.getDisplay().getTurtle(1);
	}
	
	public void showMessage(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(controller.getResources().getString("ErrorTitle"));
		alert.setHeaderText(controller.getResources().getString("ErrorHeader"));
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public void showHelp() {
		HelpView helpView = new HelpView(controller);
		helpView.show();
	}
	
	public void showSettings() {
		SettingsView settings = new SettingsView(controller, turtleWindow.getDisplay(), stage);
		settings.show();
	}
}
