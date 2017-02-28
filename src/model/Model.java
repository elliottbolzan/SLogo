package model;

import controller.Controller;
import javafx.collections.FXCollections;
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
		return FXCollections.observableArrayList(new Variable("test1", 1), new Variable("test2", 4), new Variable("test3", 5));
	}

	@Override
	public ObservableList<String> getUserDefinedCommands() {
		return FXCollections.observableArrayList("test1", "test2", "test3");
	}
	
	public void setLanguage(String language) {
		parser.setLanguage(language);
	}

	public String getLanguage() {
		return parser.getLanguage();
	}
}
