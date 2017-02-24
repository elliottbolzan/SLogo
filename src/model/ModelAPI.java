package model;

import javafx.collections.ObservableList;

public interface ModelAPI {

	public Command parse(String string);

	public ObservableList<String> getHistory();
	
	public ObservableList<Variable> getVariables();
	
	public ObservableList<String> getUserDefinedCommands();
	
}
