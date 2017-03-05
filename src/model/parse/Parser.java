package model.parse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.StateStorage;
import model.Variable;
import model.commands.Command;
import model.commands.control.MakeVariableCommand;
import model.commands.control.UserCommand;
import utils.BadInputException;
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
	private String path = "resources/languages/";

	private Controller controller;
	private ResourceBundle resources = ResourceBundle.getBundle(path + language);

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
		resources = ResourceBundle.getBundle(path + language);
		stringToCommandMap.updateMap(language);
	}

	public String getLanguage() {
		return language;
	}

	public StateStorage getStateStorage() {
		return stateStorage;
	}

	public ObservableList<Variable> getVariables() {
		return stateStorage.getVariables();
	}

	public ObservableList<String> getUserDefinedCommands() {
		return stateStorage.getUserDefinedCommands();
	}

	@Override
	public void parse(String input) throws BadInputException {
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

	protected double internalParse(String input) throws BadInputException {
		double result = 0.0;
		List<String> tokens = Arrays.asList(input.split("\\s+"));
		result = preOrderEvaluation(tokens);
		if (!commands.isEmpty()) {
			result = inputToCommands(commands, arguments);
		}
		return result;
	}

	private double preOrderEvaluation(List<String> tokens) throws BadInputException {

		double mostRecentReturnValue = 0.0;
		if (tokens != null) {
			int arrayLength = tokens.size();
			for (int i = 0; i < arrayLength; i++) {
				String token = tokens.get(i);

				if (resources.getString("If").equals(token)) {
					i = (new IfBlockHandler(this)).handleIf(i, tokens);
				} else if (resources.getString("IfElse").equals(token)) {
					i = (new IfElseBlockHandler(this)).handleIfElse(i, tokens);
				} else if (resources.getString("Repeat").equals(token)) {
					i = (new RepeatBlockHandler(this)).handleRepeat(i, tokens);
				} else if (resources.getString("DoTimes").equals(token)) {
					i = (new DoTimesHandler(this)).handleDoTimes(i, tokens);
				} else if (resources.getString("For").equals(token)) {
					i = (new ForLoopHandler(this)).handleForLoop(i, tokens);
				} else if (resources.getString("MakeUserInstruction").equals(token)) {
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
				if (tokens.size() == 1 && Identify.determineType((tokens.get(0))) == TokenType.CONSTANT) {
					return Double.parseDouble(tokens.get(0));
				}
			}
		}
		return mostRecentReturnValue;
	}

	private double inputToCommands(Stack<Command> commandStack, Stack<Double> argumentStack) throws BadInputException {
		double result = 0.0;
		int size = commandStack.size();
		for (int i = 0; i < size; i++) {
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
				} catch (BadInputException e) {
					commands.clear();
					arguments.clear();
					variables.clear();
					text.clear();
					throw new BadInputException("Command not found at runtime");

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

	private int handleTo(int index, List<String> tokens) throws BadInputException {
		index = index + 1;

		String expression = tokens.get(index);

		index += 2;

		internalParse(expression.trim());

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

	private List<Double> createArgumentList(Stack<Double> argumentStack, int numberOfParameters) {
		List<Double> arguments = Arrays.asList(new Double[numberOfParameters]);
		for (int i = numberOfParameters - 1; i >= 0; i--) {
			arguments.set(i, argumentStack.pop());
		}
		return arguments;
	}
}