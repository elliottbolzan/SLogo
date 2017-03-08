package model;

import javafx.collections.ObservableList;
import view.visualization.Turtle;

public interface ModelAPI {

	public void parse(String string);

	public ObservableList<String> getHistory();

	public ObservableList<Variable> getVariables();

	public ObservableList<String> getUserDefinedCommands();

	public void setLanguage(String language);
	
	public String getLanguage();

	ActiveTurtles getActiveTurtles();

	Turtle getTurtle(int index);

}
