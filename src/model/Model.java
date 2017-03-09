package model;

import controller.Controller;
import javafx.collections.ObservableList;
import model.parser.TreeParser;
import model.parser.nodes.Node;

public class Model implements ModelAPI {

	private Controller controller;
	private TreeParser parser;
	
	public Model(Controller controller) {
		this.controller = controller;
		parser = new TreeParser(controller);
	}
	
	public Node parse(String string, boolean addToHistory) {
		return parser.parse(string, addToHistory);
	}
	
	public ObservableList<String> getHistory() {
		return parser.getHistory();
	}

	@Override
	public ObservableList<Variable> getVariables() {
		return parser.getVariables();
	}

	@Override
	public ObservableList<String> getUserDefinedCommands() {
		return parser.getUserDefinedCommands();
	}
	
	@Override 
	public ObservableList<IndexedColor> getColorPalette() {
		return parser.getColorPalette();
	}
	
	public void setLanguage(String language) {
		parser.setLanguage(language);
	}

	public String getLanguage() {
		return parser.getLanguage();
	}
}
