package model;

import javafx.collections.ObservableList;
import model.parser.nodes.Node;

public interface ModelAPI {
	public Node parse(String input, boolean addToHistory);
	public ObservableList<String> getHistory();
	public ObservableList<Variable> getVariables();
	public ObservableList<String> getUserDefinedCommands();
	public ObservableList<IndexedColor> getColorPalette();
	public ObservableList<IndexedImage> getImagePalette();
	
	public void setLanguage(String language);
	
	public String getLanguage();
}