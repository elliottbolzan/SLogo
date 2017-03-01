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
	private Stack<Double> arguments;

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
					arguments.push(Double.parseDouble(s[i]));
				}
			}
		}
	}

	private ArrayList<Command> inputToCommands(Stack<String> commandStack, Stack<Double> argumentStack) {
		ArrayList<Command> actualCommands = new ArrayList<>();
		int size = commandStack.size();
		for (int i = 0; i < size; i++) {
			String s = commandStack.pop();
			Command toExecute = stringToCommandMap.get(s);
			if (argumentStack.size() == 0 && toExecute.numParameters() != 0) {
				commandStack.push(s);
				continue;
			}
			double evaluation = toExecute.execute(createArguments(argumentStack, toExecute.numParameters()), controller.getTurtle(), controller);
			if (!(commandStack.size() == 0)) {
				argumentStack.push(evaluation);
			}
			else {
				controller.print(Double.toString(evaluation));
			}
		}
		return actualCommands;
	}
	
	private double[] createArguments(Stack<Double> argumentStack, int numberOfParameters) {
		double[] arguments = new double[numberOfParameters];
		for (int i = 0; i < numberOfParameters; i++) {
			arguments[i] = argumentStack.pop();
		}
		return arguments;
	}
	
}
