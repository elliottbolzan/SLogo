package view;

import java.awt.Dimension;

import utils.Point;
import view.console.Console;
import view.panel.Panel;
import view.visualization.Turtle;
import view.visualization.TurtleDisplay;
import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

/**
 * @author Elliott Bolzan
 *
 * This class serves as the view component of the application.
 * It initializes and controls the user interface.
 */
public class Workspace extends BorderPane implements ViewAPI {
	
	private Controller controller;
	private Console console;
	private TurtleDisplay turtleDisplay;
	private Panel panel;
	private SplitPane pane;

	/**
	 * Creates a View object.
	 * @param stage the program's primary window.
	 * @return a View object.
	 */
	public Workspace(Controller controller) {
		this.controller = controller;
		setPadding(new Insets(4));
		setup();
	}
	
	private void setup() {
		pane = new SplitPane();
		console = new Console(this, 0);
		console.focusedProperty().addListener(new ChangeListener<Boolean>(){
	        @Override
	        public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
	            System.out.println("focus changed");
	        }
	    });
		turtleDisplay = new TurtleDisplay();
		panel = new Panel(this, 1);
		console.focus();
		pane.getItems().addAll(console, turtleDisplay, panel);
		pane.setDividerPositions(0.3, 0.75);
		setCenter(pane);
	}
	
	public Console getConsole() {
		return console;
	}
	
	public Controller getController() {
		return controller;
	}
	
	public SplitPane getPane() {
		return pane;
	}
	
	public TurtleDisplay getDisplay() {
		return turtleDisplay;
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
		turtleDisplay.moveTurtle(1, point);
	}


	@Override
	public void turn(double degrees) {
		//TODO require turtle id
		turtleDisplay.turnTurtle(1, degrees);
	}


	@Override
	public void setPenDown(boolean down) {
		//TODO require turtle id
		turtleDisplay.setPenDown(1, down);
	}


	@Override
	public void setTurtleVisible(boolean visible) {
		//TODO require turtle id
		turtleDisplay.setTurtleVisible(1, visible);
	}


	@Override
	public void clearDisplay() {
		turtleDisplay.clear();
	}


	@Override
	public Dimension getDisplaySize() {
		return turtleDisplay.getDimensions();
	}

	
	@Override
	public Turtle getTurtle() {
		//TODO require turtle id
		return turtleDisplay.getTurtle(1);
	}
	
	public void showMessage(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(controller.getResources().getString("ErrorTitle"));
		alert.setHeaderText(controller.getResources().getString("ErrorHeader"));
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public void showSettings() {
		/*SettingsView settings = new SettingsView(controller, turtleDisplay, stage);
		settings.show();*/
	}
}
