package model.parse;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.commands.Command;

/**
 * @author Zapata This is the class that will take the user-input and parse it
 *         from a string into a command (or if multiple commands the very last
 *         command executed. This class will also have an internal HashMap,
 *         whose keys are either the command history, or user-defined commands
 *         with buckets that are Command ArrayLists.
 */
public class parseUserInput implements Parser {

	private HashMap<String, ArrayList<Command>> parseMap;
	private HashMap<String, String[]> stringToCommandMap;
	// private Queue<Command> commandQueue;
	private Stack<String> commandStrings;
	private Stack<Integer> arguments;
	// private Queue<Command> commandQueue;
	private Command currentCommand;

	public parseUserInput() {
		parseMap = new HashMap<String, ArrayList<Command>>();
		parseMap.put("history", new ArrayList<Command>());
		// commandQueue = new LinkedList<Command>();
	}
	
	public void setLanguage(String langauge) {
		// DO SOMETHING
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
		ArrayList<Command> list = new ArrayList<>();
		preOrderEvaluation(tokens);

		return list;
	}

	@Override
	public ObservableList<String> getHistory() {
		ArrayList<Command> history = parseMap.get("history");
		ObservableList<String> stringHistory = FXCollections.observableArrayList();
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
		if (!parseMap.keySet().contains(newCommand)) {

		}
		// Now parse through the commands that they use in order to add those
		// methods to the ArrayList
		// in the HashMap.
	}

	private void preOrderEvaluation(String[] s) {
		if (s != null) {
			int arrayLength = s.length;
			for (int i = 0; i < arrayLength; i++) {
				if (Character.isLetter(s[i].charAt(0))) {
					commandStrings.push(s[i]);
				} else if (Character.isDigit(s[i].charAt(0))) {
					arguments.push(Integer.parseInt(s[i]));
				}
			}
		}
	}

	private ArrayList<Command> inputToCommands(Stack<String> commandStack, Stack<Integer> argumentStack){
		Stack<String> stringsOfCommands = new Stack<>();
		Stack<Integer> argumentIntegers = new Stack<>();
		stringsOfCommands = (Stack<String>) commandStack.clone();
		argumentIntegers = (Stack<Integer>) argumentStack.clone();
		ArrayList<Command> actualCommands = new ArrayList<>();
		for (int i = 0; i < commandStack.size(); i++) {
			String s = new String();
			int numOfParameters = 0;
			s = commandStack.pop();
			Command here = new Command();
			here = new stringToCommandMap.get(s)(argumentIntegers.pop());
			
		}
		return actualCommands;
	}

}
