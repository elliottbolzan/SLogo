package model;

import controller.Controller;
import javafx.collections.ObservableList;
import model.parse.Parser;

public class Model implements ModelAPI {

	private Controller controller;
	private Parser parser;
	
	public Model(Controller controller) {
		this.controller = controller;
		parser = new Parser(controller);
	}
	
	public void parse(String string) {
		parser.parse(string);
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
	
	public void setLanguage(String language) {
		parser.setLanguage(language);
	}

	public String getLanguage() {
		return parser.getLanguage();
	}
}
