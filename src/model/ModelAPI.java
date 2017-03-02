package model;

import javafx.collections.ObservableList;
import utils.BadInputException;

public interface ModelAPI {

	public void parse(String string) throws BadInputException;

	public ObservableList<String> getHistory();

	public ObservableList<Variable> getVariables();

	public ObservableList<String> getUserDefinedCommands();

	public void setLanguage(String language);
	
	public String getLanguage();

}
