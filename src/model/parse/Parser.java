package model.parse;

import java.util.ArrayList;
import java.util.Stack;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.commands.Command;

/**
 * @author Alexander Zapata This is the class that will take the user-input and 
 *         parse it from a string into a command (or if multiple commands the very last
 *         command executed. This class will also have an internal HashMap,
 *         whose keys are either the command history, or user-defined commands
 *         with buckets that are Command ArrayLists.
 */
public class Parser implements ParserAPI {
	
	private Controller controller;
	private String language = "English";

	private ObservableList<String> historyList;
	private CommandMap stringToCommandMap;
	private Stack<String> commands;
	private Stack<Integer> arguments;
	private Command currentCommand;

	public Parser(Controller c) {
		controller = c;
		historyList = FXCollections.observableList(new ArrayList<String>());
		arguments = new Stack<>();
		commands = new Stack<String>();
		stringToCommandMap = new CommandMap();
	}

	public void setLanguage(String language) {
		this.language = language;
		stringToCommandMap.updateMap(language);
	}
	
	public String getLanguage() {
		return language;
	}

	public Command getCurrentCommand() {
		return currentCommand;
	}

	public void setCurrentCommand(Command currentCommand) {
		this.currentCommand = currentCommand;
	}

	@Override
	public void parse(String input) {
		historyList.add(0, input);
		String[] tokens = input.split(" ");
		preOrderEvaluation(tokens);
		inputToCommands(commands, arguments);
	}

	@Override
	public ObservableList<String> getHistory() {
		return historyList;
	}

	@Override
	public String getPreviousCommand(int k) {
		return historyList.get(0);
	}

	@Override
	public void addUserDefinedCommand(String newCommand) {
		/*if (!parseMap.keySet().contains(newCommand)) {
		}
		// Now parse through the commands that they use in order to add those
		// methods to the ArrayList
		// in the HashMap. */
	}

	private void preOrderEvaluation(String[] s) {
		if (s != null) {
			int arrayLength = s.length;
			for (int i = 0; i < arrayLength; i++) {
				if (Character.isLetter(s[i].charAt(0))) {
					commands.push(s[i]);
				} else if (Character.isDigit(s[i].charAt(0))) {
					arguments.push(Integer.parseInt(s[i]));
				}
			}
		}
	}

	private ArrayList<Command> inputToCommands(Stack<String> commandStack, Stack<Integer> argumentStack) {
		ArrayList<Command> actualCommands = new ArrayList<>();
		int size = commandStack.size();
		for (int i = 0; i < size; i++) {
			String s = new String();
			s = commandStack.pop();
			Command toExecute;
			int evaluation = 0;
			toExecute = stringToCommandMap.get(s);
			setCurrentCommand(toExecute);
			int parameterNumber = toExecute.numParameters();
			if (argumentStack.size() == 0) {
				commandStack.push(s);
				continue;
			}
			if (parameterNumber == 1) {
				double[] firstArg = new double[1];
				firstArg[0] = argumentStack.pop();
				evaluation = (int) toExecute.execute(firstArg, controller.getTurtle(), controller);
			} else if (parameterNumber == 2) {
				double[] firstArg = new double[2];
				firstArg[0] = argumentStack.pop();
				firstArg[1] = argumentStack.pop();
				evaluation = (int) toExecute.execute(firstArg, controller.getTurtle(), controller);
			} else {
				double[] firstArg = null;
				evaluation = (int) toExecute.execute(firstArg, controller.getTurtle(), controller);
			}
			if (!(commandStack.size() == 0)) {
				argumentStack.push(evaluation);
			}
		}
		return actualCommands;
	}
}
