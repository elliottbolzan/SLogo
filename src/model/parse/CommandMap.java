package model.parse;

import java.util.HashMap;

import model.commands.*;

public class CommandMap {

	private HashMap<String, Command> stringToCommandMap;

	public CommandMap(){
		stringToCommandMap = new HashMap<String, Command>();
		stringToCommandMap.put("forward", new FowardCommand(0, null));
		stringToCommandMap.put("fd", new FowardCommand(0, null));
		stringToCommandMap.put("set", new SetXYCommand(0, null));
		stringToCommandMap.put("QUOTIENT", new QuotientCommand(0, null));
	}
}
