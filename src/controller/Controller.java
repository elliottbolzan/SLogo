package controller;

import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import model.IndexedColor;
import model.IndexedImage;
import model.Model;
import model.ModelAPI;
import model.Variable;
import model.parser.nodes.Node;
import view.Workspace;
import view.WorkspaceBrowser;
import view.ViewAPI;
import view.visualization.TurtleManager;

/**
 * @author Elliott Bolzan
 *
 *         This class serves as a bridge between the front-end and the back-end.
 *         Holds references to both the view and the model. Implements both the
 *         ViewAPI and ModelAPI interfaces, to facilitate communication between
 *         both components of the project.
 */
public class Controller implements ViewAPI, ModelAPI {

	private Workspace workspace;
	private Model model;
	private ResourceBundle resources;

	/**
	 * Creates a Controller
	 * @param browser the WorkspaceBrowser that created this Controller.
	 */
	public Controller(WorkspaceBrowser browser) {
		resources = ResourceBundle.getBundle("resources/UserInterface");
		model = new Model(this);
		workspace = new Workspace(this);
	}

	public ResourceBundle getResources() {
		return resources;
	}

	public Workspace getView() {
		return workspace;
	}

	/* (non-Javadoc)
	 * @see view.ViewAPI#printToConsole(java.lang.String)
	 */
	@Override
	public void printToConsole(String string) {
		workspace.printToConsole(string);
	}

	/* (non-Javadoc)
	 * @see view.ViewAPI#clearConsole()
	 */
	@Override
	public void clearConsole() {
		workspace.clearConsole();
	}

	/* (non-Javadoc)
	 * @see view.ViewAPI#clearDisplay()
	 */
	@Override
	public void clearDisplay() {
		workspace.clearDisplay();
	}

	/* (non-Javadoc)
	 * @see view.ViewAPI#getTurtleManager()
	 */
	@Override
	public TurtleManager getTurtleManager() {
		return workspace.getTurtleManager();
	}

	/* (non-Javadoc)
	 * @see model.ModelAPI#parse(java.lang.String, boolean)
	 */
	@Override
	public Node parse(String string, boolean addToHistory) {
		return model.parse(string, addToHistory);
	}

	/* (non-Javadoc)
	 * @see model.ModelAPI#getHistory()
	 */
	@Override
	public ObservableList<String> getHistory() {
		return model.getHistory();
	}

	/* (non-Javadoc)
	 * @see model.ModelAPI#getVariables()
	 */
	@Override
	public ObservableList<Variable> getVariables() {
		return model.getVariables();
	}

	/* (non-Javadoc)
	 * @see model.ModelAPI#getUserDefinedCommands()
	 */
	@Override
	public ObservableList<String> getUserDefinedCommands() {
		return model.getUserDefinedCommands();
	}

	/* (non-Javadoc)
	 * @see model.ModelAPI#getColorPalette()
	 */
	@Override
	public ObservableList<IndexedColor> getColorPalette() {
		return model.getColorPalette();
	}

	/* (non-Javadoc)
	 * @see model.ModelAPI#getImagePalette()
	 */
	@Override
	public ObservableList<IndexedImage> getImagePalette() {
		return model.getImagePalette();
	}

	/* (non-Javadoc)
	 * @see model.ModelAPI#setLanguage(java.lang.String)
	 */
	@Override
	public void setLanguage(String language) {
		model.setLanguage(language);
	}

	/* (non-Javadoc)
	 * @see model.ModelAPI#getLanguage()
	 */
	@Override
	public String getLanguage() {
		return model.getLanguage();
	}

	/* (non-Javadoc)
	 * @see view.ViewAPI#showMessage(java.lang.String)
	 */
	@Override
	public void showMessage(String message) {
		workspace.showMessage(message);
	}

	/* (non-Javadoc)
	 * @see view.ViewAPI#setBackgroundColor(javafx.scene.paint.Color)
	 */
	@Override
	public void setBackgroundColor(Color color) {
		workspace.setBackgroundColor(color);
	}

}
