package parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import commands.Command;

/**
 * @author Zapata
 * This is the class that will take the user-input and parse it from a string into a command (or if multiple commands
 * the very last command executed.
 * This class will also have an internal HashMap, whose keys are either the command history, or user-defined commands
 * with buckets that are Command ArrayLists.
 */
public class parseUserInput implements Parser {

	private HashMap<String, ArrayList<Command>> parseMap;
	//private Queue<Command> commandQueue;
	private Command currentCommand;

	public parseUserInput() {
		parseMap = new HashMap<String, ArrayList<Command>>();
		parseMap.put("history", new ArrayList<Command>());
		//commandQueue = new LinkedList<Command>();
	}

	public Command getCurrentCommand() {
		return currentCommand;
	}

	public void setCurrentCommand(Command currentCommand) {
		this.currentCommand = currentCommand;
	}

	@Override
	public ArrayList<Command> parse(String input) {
		String[] tokens = input.split(" ");
		ArrayList<Command> parsedCommandList = new ArrayList<>();
		recursivePreOrderEvaluation(tokens, parsedCommandList);
		//Ask team-mates about how to get responses from properties folder.
		//Or actually, maybe I should make an Enums file that goes from String to Command somehow.
		return null;
	}

	@Override
	public List<String> getHistory() {
		ArrayList<Command> history = parseMap.get("history");
		ArrayList<String> stringHistory = new ArrayList<>();
		for (int i = 0; i < history.size(); i++) {
			stringHistory.add(history.get(i).toString());
		}
		return stringHistory;
	}

	@Override
	public String getPreviousCommand(int k) {
		return parseMap.get("history").get(k).toString();
	}

	@Override
	public void addUserDefinedCommand(String newCommand) {
		if(!parseMap.keySet().contains(newCommand)){
			
		}
		//Now parse through the commands that they use in order to add those methods to the ArrayList
		//in the HashMap.
	}
	
	private void recursivePreOrderEvaluation(String[] s, ArrayList<Command> emptyCommandList){
		
	}

}
