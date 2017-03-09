package view;

import view.input.InputContainer;
import view.input.ShellView;
import view.panel.Panel;
import view.visualization.TurtleDisplay;
import view.visualization.TurtleManager;

import java.util.ResourceBundle;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * @author Elliott Bolzan
 *
 *         This class serves as the view component of the application. It
 *         initializes and controls the user interface.
 */
public class Workspace extends BorderPane implements ViewAPI {

	private WorkspaceBrowser browser;
	private Controller controller;
	private InputContainer inputContainer;
	private TurtleDisplay turtleDisplay;
	private Panel panel;
	private SplitPane pane;

	/**
	 * Creates a View object.
	 * 
	 * @param stage
	 *            the program's primary window.
	 * @return a View object.
	 */
	public Workspace(WorkspaceBrowser browser, Controller controller) {
		this.browser = browser;
		this.controller = controller;
		setPadding(new Insets(4));
		setup();
	}

	private void setup() {
		pane = new SplitPane();
		inputContainer = new InputContainer(this, 0);
		turtleDisplay = new TurtleDisplay(this);
		panel = new Panel(this, 1);
		pane.getItems().addAll(inputContainer, turtleDisplay, panel);
		pane.setDividerPositions(0.3, 0.75);
		loadPreferences();
		setCenter(pane);
	}

	public WorkspaceBrowser getBrowser() {
		return browser;
	}

	public ShellView getShell() {
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
	public void clearDisplay() {
		turtleDisplay.clear();
	}

	@Override
	public TurtleManager getTurtleManager() {
		return turtleDisplay.getTurtleManager();
	}

	public void showMessage(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(controller.getResources().getString("ErrorTitle"));
		alert.setHeaderText(controller.getResources().getString("ErrorHeader"));
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void loadPreferences() {
		ResourceBundle resources = ResourceBundle.getBundle("resources/WorkspaceSettings");
		try {
			Color color = Color.valueOf(resources.getString("Color"));
			int turtleNumber = Integer.valueOf(resources.getString("TurtleNumber"));
			String language = resources.getString("Language");
			String defaultScript = resources.getString("File");
			inputContainer.getScriptView().readFileIn(defaultScript);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
