package view;

import view.components.Factory;
import view.input.InputContainer;
import view.input.ShellView;
import view.panel.Panel;
import view.visualization.TurtleDisplay;
import view.visualization.TurtleManager;
import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * @author Elliott Bolzan
 *
 *         This class serves as the primary view component for the application.
 *         It initializes and controls the user interface for one Workspace. It
 *         also holds references to an InputContainer, a TurtleDisplay, and a
 *         Panel.
 */
public class Workspace extends BorderPane implements ViewAPI {

	private Controller controller;
	private Defaults defaults;
	private InputContainer inputContainer;
	private TurtleDisplay turtleDisplay;
	private Panel panel;
	private SplitPane pane;

	/**
	 * Creates a Workspace.
	 * @param controller the Workspace's controller.
	 */
	public Workspace(Controller controller) {
		this.controller = controller;
		setup();
	}

	/**
	 * Initializes the Workspace's components.
	 */
	private void setup() {
		pane = new SplitPane();
		defaults = new Defaults(this, getController().getResources().getString("WorkspaceSettingsPath"));
		controller.setLanguage(defaults.getLanguage());
		inputContainer = new InputContainer(this, 0);
		inputContainer.getScriptView().readFileIn(defaults.getScriptPath());
		turtleDisplay = new TurtleDisplay(this, defaults.getNumberOfTurtles(), defaults.getTurtleImage(),
				defaults.getBackgroundColor());
		panel = new Panel(this, 1);
		pane.getItems().addAll(inputContainer, turtleDisplay, panel);
		pane.setDividerPositions(0.3, 0.75);
		setPadding(new Insets(4));
		setCenter(pane);
	}

	public Defaults getDefaults() {
		return defaults;
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

	/* (non-Javadoc)
	 * @see view.ViewAPI#printToConsole(java.lang.String)
	 */
	@Override
	public void printToConsole(String string) {
		inputContainer.getShellView().print(string);
	}

	/* (non-Javadoc)
	 * @see view.ViewAPI#clearConsole()
	 */
	@Override
	public void clearConsole() {
		inputContainer.getShellView().clear();
	}

	/* (non-Javadoc)
	 * @see view.ViewAPI#clearDisplay()
	 */
	@Override
	public void clearDisplay() {
		turtleDisplay.clear();
	}

	/* (non-Javadoc)
	 * @see view.ViewAPI#getTurtleManager()
	 */
	@Override
	public TurtleManager getTurtleManager() {
		return turtleDisplay.getTurtleManager();
	}

	/* (non-Javadoc)
	 * @see view.ViewAPI#setBackgroundColor(javafx.scene.paint.Color)
	 */
	@Override
	public void setBackgroundColor(Color color) {
		turtleDisplay.setBackgroundColor(color);
	}

	/* (non-Javadoc)
	 * @see view.ViewAPI#showMessage(java.lang.String)
	 */
	@Override
	public void showMessage(String message) {
		Factory factory = new Factory(controller.getResources());
		factory.makeAlert(AlertType.ERROR, "ErrorTitle", "ErrorHeader", message).showAndWait();
	}

}
