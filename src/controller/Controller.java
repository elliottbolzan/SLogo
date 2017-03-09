package controller;

import java.awt.Dimension;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
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
 */
public class Controller implements ViewAPI, ModelAPI {
	
	private Workspace workspace;
	private Model model;
	private ResourceBundle resources; 

	/**
	 * 
	 */
	public Controller(WorkspaceBrowser browser) {
		resources = ResourceBundle.getBundle("resources/UserInterface");
		model = new Model(this);
		workspace = new Workspace(browser, this);
	}
	
	public ResourceBundle getResources() {
		return resources;
	}
	
	public Workspace getView() {
		return workspace;
	}

	@Override
	public void print(String string) {
		workspace.print(string);
	}

	@Override
	public void clearConsole() {
		workspace.clearConsole();
	}

	@Override
	public void clearDisplay() {
		workspace.clearDisplay();
	}
	
	@Override
	public TurtleManager getTurtleManager() {
		return workspace.getTurtleManager();
	}
	
	@Override
	public Node parse(String string, boolean addToHistory) {
		return model.parse(string, addToHistory);
	}

	@Override
	public ObservableList<String> getHistory() {
		return model.getHistory();
	}

	@Override
	public ObservableList<Variable> getVariables() {
		return model.getVariables();
	}

	@Override
	public ObservableList<String> getUserDefinedCommands() {
		return model.getUserDefinedCommands();
	}
	
	@Override
	public void setLanguage(String language) {
		model.setLanguage(language);
	}
	
	@Override
	public String getLanguage() {
		return model.getLanguage();
	}

	@Override
	public void showMessage(String message) {
		workspace.showMessage(message);
	}

	@Override
	public void setBackgroundColorAtIndex(int index) {
		workspace.setBackgroundColorAtIndex(index);
	}
}
