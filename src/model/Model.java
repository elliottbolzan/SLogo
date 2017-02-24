package model;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model implements ModelAPI {

	private Controller controller;
	
	public Model(Controller controller) {
		this.controller = controller;
	}
	
	public Command parse(String string) {
		return new Command();
	}
	
	public ObservableList<String> getHistory() {
		return FXCollections.observableArrayList("test1", "test2", "test3");
	}

	@Override
	public ObservableList<Variable> getVariables() {
		return FXCollections.observableArrayList(new Variable("test1", 1), new Variable("test2", 4), new Variable("test3", 5));
	}

	@Override
	public ObservableList<String> getUserDefinedCommands() {
		return FXCollections.observableArrayList("test1", "test2", "test3");
	}

}
