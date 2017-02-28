package model.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import model.commands.*;

public class CommandMap {

	private HashMap<String, Command> stringToCommandMap;
	private String path = "resources/languages/";
	private ResourceBundle myResources;

	public CommandMap() {
		updateMap("English");
	}

	public void updateMap(String language) {
		myResources = ResourceBundle.getBundle(path + language);
		stringToCommandMap = new HashMap<String, Command>();
		createCommands();
	}
	
	private void createCommands() {
		addCommand(myResources.getString("Forward"), new ForwardCommand(1, null));
		addCommand(myResources.getString("Backward"), new BackCommand(1, null));
	}

	private void addCommand(String string, Command command) {
		String[] names = string.split("[|]");
		for (String name : names) {
			stringToCommandMap.put(name, command);
		}
	}

	public Command get(String key) {
		return stringToCommandMap.get(key);
	}

}
