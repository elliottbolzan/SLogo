package model;

import controller.Controller;
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
		return null;
	}

	@Override
	public ObservableList<Variable> getVariables() {
		return null;
	}

	@Override
	public ObservableList<String> getUserDefinedCommands() {
		return null;
	}

}
