package model;

import javafx.collections.ObservableList;
import view.visualization.Turtle;
import model.parser.nodes.Node;

public interface ModelAPI {

	public Node parse(String input, boolean addToHistory);

	public ObservableList<String> getHistory();

	public ObservableList<Variable> getVariables();

	public ObservableList<String> getUserDefinedCommands();

	public void setLanguage(String language);
	
	public String getLanguage();

	ActiveTurtles getActiveTurtles();

	Turtle getTurtle(int index);

}
