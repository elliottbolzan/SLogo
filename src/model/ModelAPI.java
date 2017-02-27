package model;

import javafx.collections.ObservableList;

public interface ModelAPI {

	public void parse(String string);

	public ObservableList<String> getHistory();

	public ObservableList<Variable> getVariables();

	public ObservableList<String> getUserDefinedCommands();

	public void setLanguage(String language);

}
