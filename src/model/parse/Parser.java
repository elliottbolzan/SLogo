package model.parse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.AbstractMap.SimpleEntry;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.StateStorage;
import model.Variable;
import model.commands.Command;
import model.commands.control.MakeVariableCommand;
import model.commands.control.UserCommand;
import model.parse.tokens.Identify;
import model.parse.tokens.TokenType;

/**
 * @author Alexander Zapata This is the class that will take the user-input and
 *         parse it from a string into a command (or if multiple commands the
 *         very last command executed. This class will also have an internal
 *         HashMap, whose keys are either the command history, or user-defined
 *         commands with buckets that are Command ArrayLists.
 */
public class Parser implements ParserAPI {

	private String language = "English";
	private Controller controller;

	private ObservableList<String> historyList;
	private CommandMap stringToCommandMap;
	private Stack<Command> commands;
	private Stack<Double> arguments;
	private Stack<String> variables;
	private Stack<String> text;
	private StateStorage stateStorage;

	public Parser(Controller c) {
		controller = c;
		historyList = FXCollections.observableList(new ArrayList<String>());
		arguments = new Stack<Double>();
		commands = new Stack<Command>();
		variables = new Stack<String>();
		text = new Stack<String>();
		stringToCommandMap = new CommandMap();
		stateStorage = new StateStorage();
	}

	public void setLanguage(String language) {
		this.language = language;
		stringToCommandMap.updateMap(language);
	}

	public String getLanguage() {
		return language;
	}

	public ObservableList<Variable> getVariables() {
		return stateStorage.getVariables();
	}

	public ObservableList<String> getUserDefinedCommands() {
		return stateStorage.getUserDefinedCommands();
	}

	@Override
	public void parse(String input) throws Exception {
		historyList.add(0, input);
		internalParse(input.trim());
	}

	@Override
	public ObservableList<String> getHistory() {
		return historyList;
	}

	@Override
	public String getPreviousCommand(int k) {
		return historyList.get(0);
	}

	private double internalParse(String input) throws NumberFormatException, Exception {
		double result = 0.0;
		List<String> tokens = Arrays.asList(input.split("\\s+"));
		result = preOrderEvaluation(tokens);
		if (!commands.isEmpty()) {
			result = inputToCommands(commands, arguments);
		}
		return result;
	}

	private double preOrderEvaluation(List<String> tokens) throws NumberFormatException, Exception {
		if (tokens.size() == 1 && Identify.determineType(tokens.get(0)) == TokenType.CONSTANT) {
			return Double.parseDouble(tokens.get(0));
		}

		double mostRecentReturnValue = 0.0;
		if (tokens != null) {
			int arrayLength = tokens.size();
			for (int i = 0; i < arrayLength; i++) {
				String token = tokens.get(i);
				if (token.equals("if")){
					i = handleIf(i, tokens);
				} else if (token.equals("ifelse")) {
					i = handleIfElse(i, tokens);
				} else if (token.equals("repeat")) {
					i = handleRepeat(i, tokens);
				} else if (token.equals("dotimes")) {
					i = handleDoTimes(i, tokens);
				} else if (token.equals("for")) {
					i = handleForLoop(i, tokens);
				} else if (token.equals("to")) {
					i = handleTo(i, tokens);
				}

				TokenType type = Identify.determineType(token);
				if (type == TokenType.COMMENT) {
					break;
				} else if (type == TokenType.CONSTANT) {
					this.addArgumentAsDouble(token);
				} else if (type == TokenType.VARIABLE) {
					int varIndex = stateStorage.getVariableIndex(new Variable(token, 0.0));
					if (varIndex != -1) {
						Variable var = stateStorage.getVariables().get(varIndex);
						this.addArgumentAsDouble(var.getValue());
					}
					variables.push(token);
				} else if (type == TokenType.COMMAND) {
					if (isBuiltInCommand(token)) {
						if (!commands.isEmpty() && (commands.peek().numParameters() <= arguments.size())) {
							mostRecentReturnValue = inputToCommands(commands, arguments);
						}
						commands.push(stringToCommandMap.get(token));
					} else if (stateStorage.getCmdList().containsKey(token)) {
						if (!commands.isEmpty()
								&& (stateStorage.getCmdList().get(token).numParameters() <= arguments.size())) {
							mostRecentReturnValue = inputToCommands(commands, arguments);
						}
						commands.push(stateStorage.getCmdList().get(token));
					} else {
						text.push(token);
					}
				}
			}
		}
		return mostRecentReturnValue;
	}

	private double inputToCommands(Stack<Command> commandStack, Stack<Double> argumentStack) {
		double result = 0.0;
		int size = commandStack.size();
		for (int i = 0; i < size; i++) {
			for(Command cmd: commandStack){
				System.out.println(i + "th iteration and size: " + commandStack.size());
			}
			Command toExecute = commandStack.pop();
			if (argumentStack.size() == 0 && toExecute.numParameters() != 0) {
				commandStack.push(toExecute);
				continue;
			}
			if ((toExecute.numParameters() <= arguments.size())) {

				Command newInstance = toExecute;
				double evaluation = 0.0; 

				try {

					if (!(toExecute instanceof UserCommand)) {
						newInstance = toExecute.getClass().newInstance();
					}

					List<Double> params = createArgumentList(argumentStack, newInstance.numParameters());
					newInstance.initialize(params, controller);

					if (newInstance instanceof MakeVariableCommand) {
						((MakeVariableCommand) newInstance).initialize(variables.pop().replace(":", ""), stateStorage);
					}

					evaluation = newInstance.getReturnValue();
					controller.handleCommand(newInstance);
				} catch (InstantiationException | IllegalAccessException e) {
					controller.getView().showMessage("Command not found at runtime.");
				}

				if (!(commandStack.size() == 0)) {
					argumentStack.push(evaluation);
					continue;
				}
				if (!(toExecute instanceof UserCommand)) {
					controller.print(Double.toString(evaluation));
				}
				result = evaluation;
			} else {
				commandStack.push(toExecute);
			}
		}
		return result;
	}

	private void addArgumentAsDouble(String token) {
		if (!commands.isEmpty()) {
			arguments.push(Double.parseDouble(token));
		}
	}

	private boolean isBuiltInCommand(String token) {
		return stringToCommandMap.keySet().contains(token);
	}

	private int handleIf(int index, List<String> tokens) throws Exception {
		index = index + 1;
		String expression = "";
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_START)) {
			expression += " " + tokens.get(index);
			index++;
		}

		double result = internalParse(expression.trim());

		String commands = "";
		index = index + 1;
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_END)) {
			commands += tokens.get(index) + " ";
			index++;
		}

		if (result != 0.0) {
			internalParse(commands.trim());
		}
		return index;
	}

	private int handleIfElse(int index, List<String> tokens) throws Exception {
		index = index + 1;
		String expression = "";
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_START)) {
			expression += " " + tokens.get(index);
			index++;
		}

		double result = internalParse(expression.trim());

		String commandsTrue = "";
		index = index + 1;
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_END)) {
			commandsTrue += tokens.get(index) + " ";
			index++;
		}

		if (result != 0.0) {
			internalParse(commandsTrue.trim());
		}

		String commandsFalse = "";
		index = index + 2;
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_END)) {
			commandsFalse += tokens.get(index) + " ";
			index++;
		}

		if (result == 0.0) {
			internalParse(commandsFalse.trim());
		}
		return index;
	}

	private int handleTo(int index, List<String> tokens) throws Exception {
		index = index + 1;

		String expression = tokens.get(index);

		index += 2;

		internalParse(expression.trim());

		// added to text our command name

		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_END)) {
			expression += " " + tokens.get(index);
			index++;
		}

		internalParse(expression.trim());

		index = index + 2;

		expression = "";

		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_END)) {
			expression += " " + tokens.get(index);
			index++;
		}

		ArrayList<String> variableNames = new ArrayList<String>();
		for (int i = 0; i < variables.size(); i++) {
			variableNames.add(variables.pop());
			i--;
		}
		Collections.reverse(variableNames);
		UserCommand command = new UserCommand(text.pop(), variableNames, expression, stateStorage);

		return index;
	}

	private int handleRepeat(int index, List<String> tokens) throws NumberFormatException, Exception {
		index = index + 1;
		String expression = "";
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_START)) {
			expression += " " + tokens.get(index);
			index++;
		}

		double numRepeats = internalParse(expression.trim());

		String commands = "";
		index = index + 1;
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_END)) {
			commands += tokens.get(index) + " ";
			index++;
		}

		for (int k = 1; k <= numRepeats; k++) {
			stateStorage.setVariable(new Variable("repcount", k));
			internalParse(commands.trim());
		}
		return index;
	}

	private int handleDoTimes(int index, List<String> tokens) throws Exception {
		index += 2;
		String variableName = tokens.get(index).replaceAll("[:]", "");
		int variableIndex = stateStorage.getVariableIndex(new Variable(variableName, 0.0));
		double variableValue = Double.parseDouble(stateStorage.getVariables().get(variableIndex).getValue());

		index += 1;
		double limit = Double.parseDouble(tokens.get(index));

		index += 3;
		String commands = "";
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_END)) {
			commands += tokens.get(index) + " ";
			index++;
		}

		for (double k = variableValue; k <= limit; k++) {
			stateStorage.setVariable(new Variable(variableName, k));
			internalParse(commands.trim());
		}
		return index;
	}

	private int handleForLoop(int index, List<String> tokens) throws Exception {
		index += 2;
		String variableName = tokens.get(index).replaceAll("[:]", "");
		index += 1;
		double variableStart = Double.parseDouble(tokens.get(index));
		index += 1;
		double variableEnd = Double.parseDouble(tokens.get(index));
		index += 1;
		double variableIncrement = Double.parseDouble(tokens.get(index));

		index += 3;
		String commands = "";
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_END)) {
			commands += tokens.get(index) + " ";
			index++;
		}

		for (double k = variableStart; k <= variableEnd; k += variableIncrement) {
			stateStorage.setVariable(new Variable(variableName, k));
			internalParse(commands.trim());
		}
		return index;
	}

	private List<Double> createArgumentList(Stack<Double> argumentStack, int numberOfParameters) {
		List<Double> arguments = Arrays.asList(new Double[numberOfParameters]);
		for (int i = numberOfParameters - 1; i >= 0; i--) {
			arguments.set(i, argumentStack.pop());
		}
		return arguments;
	}

}
