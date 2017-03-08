package view;

import java.awt.Dimension;

import utils.Point;
import view.input.InputContainer;
import view.input.ShellView;
import view.panel.Panel;
import view.visualization.KeyHandler;
import view.visualization.Turtle;
import view.visualization.TurtleDisplay;
import view.visualization.WorkspaceBrowser;
import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

/**
 * @author Elliott Bolzan
 *
 * This class serves as the view component of the application.
 * It initializes and controls the user interface.
 */
public class Workspace extends BorderPane implements ViewAPI {
	
	private WorkspaceBrowser browser;
	private Controller controller;
	private InputContainer inputContainer;
	private TurtleDisplay turtleDisplay;
	private Panel panel;
	private SplitPane pane;
	//private KeyHandler handler;

	/**
	 * Creates a View object.
	 * @param stage the program's primary window.
	 * @return a View object.
	 */
	public Workspace(WorkspaceBrowser browser, Controller controller) {
		this.browser = browser;
		this.controller = controller;
		setPadding(new Insets(4));
		setup();
	}
	
	private void setup() {
		//handler = new KeyHandler();
		pane = new SplitPane();
		//shellView = new ShellView(this, 0);
		inputContainer = new InputContainer(this, 0);
		turtleDisplay = new TurtleDisplay(this);
		panel = new Panel(this, 1);
		//shellView.focus();
		pane.getItems().addAll(inputContainer, turtleDisplay, panel);
		pane.setDividerPositions(0.3, 0.75);
		setCenter(pane);
		//setOnKeyPressed(e -> keyPressed(e));
	}
	
	public WorkspaceBrowser getBrowser() {
		return browser;
	}
	
	public ShellView getConsole() {
		return inputContainer.getShellView();
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
		inputContainer.getShellView().print(string);
	}


	@Override
	public void clearConsole() {
		inputContainer.getShellView().clear();
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
	
	/*private void keyPressed(KeyEvent e) {
		String expression = handler.keyPressed(e);
		System.out.println(expression);
		if (!(expression.equals(""))) {
			controller.parse(expression);
		}
	}*/
	
}
