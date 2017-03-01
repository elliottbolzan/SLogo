package model.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.AbstractMap.SimpleEntry;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.commands.Command;

/**
 * @author Alexander Zapata This is the class that will take the user-input and
 *         parse it from a string into a command (or if multiple commands the
 *         very last command executed. This class will also have an internal
 *         HashMap, whose keys are either the command history, or user-defined
 *         commands with buckets that are Command ArrayLists.
 */
public class Parser implements ParserAPI {

	private String path = "resources/languages/Syntax";
	private String type;
	private String regEx;
	private String ErrorStatement = "No Matching Commands";

	private Controller controller;
	private String language = "English";

	private ObservableList<String> historyList;
	private CommandMap stringToCommandMap;
	private Stack<String> commands;
	private Stack<Double> arguments;
	private List<Entry<String, Pattern>> mySymbols;

	public Parser(Controller c) {
		controller = c;
		historyList = FXCollections.observableList(new ArrayList<String>());
		mySymbols = new ArrayList<>();
		arguments = new Stack<>();
		commands = new Stack<String>();
		stringToCommandMap = new CommandMap();
		type = "Constant";
		regEx = ResourceBundle.getBundle(path).getString(type);
		mySymbols.add(new SimpleEntry<>(type, Pattern.compile(regEx, Pattern.CASE_INSENSITIVE)));
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
		/*
		 * if (!parseMap.keySet().contains(newCommand)) { } // Now parse through
		 * the commands that they use in order to add those // methods to the
		 * ArrayList // in the HashMap.
		 */
	}

	private void preOrderEvaluation(String[] s) {
		if (s != null) {
			int arrayLength = s.length;
			for (int i = 0; i < arrayLength; i++) {
				boolean isCommand = stringToCommandMap.keySet().contains(s[i]);
				if (isCommand) {
					commands.push(s[i]);
				}else if(!commands.isEmpty() && !isCommand){
					String toPush = checkArgument(s[i]);
					if (!toPush.equals(ErrorStatement)) {
						arguments.push(Double.parseDouble(s[i]));
					}
				}
			}
		}
	}

	private void inputToCommands(Stack<String> commandStack, Stack<Double> argumentStack) {
		int size = commandStack.size();
		for (int i = 0; i < size; i++) {
			String s = commandStack.pop();
			Command toExecute = stringToCommandMap.get(s);
			if (argumentStack.size() == 0 && toExecute.numParameters() != 0) {
				commandStack.push(s);
				continue;
			}
			double evaluation = toExecute.execute(createArguments(argumentStack, toExecute.numParameters()),
					controller.getTurtle(), controller);
			if (!(commandStack.size() == 0)) {
				argumentStack.push(evaluation);
				continue;
			}
			controller.print(Double.toString(evaluation));
		}
	}

	private double[] createArguments(Stack<Double> argumentStack, int numberOfParameters) {
		double[] arguments = new double[numberOfParameters];
		for (int i = 0; i < numberOfParameters; i++) {
			arguments[i] = argumentStack.pop();
		}
		return arguments;
	}

	private boolean match(String text, Pattern regex) {
		return regex.matcher(text).matches();
	}

	private String checkArgument(String text) {
		final String ERROR = ErrorStatement;
		for (Entry<String, Pattern> e : mySymbols) {
			if (match(text, e.getValue())) {
				return e.getKey();
			}
		}
		return ERROR;
	}

}
