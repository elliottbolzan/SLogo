package model.parse;
import java.util.ArrayList;
import java.util.Arrays;
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
/**
 * @author Alexander Zapata This is the class that will take the user-input and
 *         parse it from a string into a command (or if multiple commands the
 *         very last command executed. This class will also have an internal
 *         HashMap, whose keys are either the command history, or user-defined
 *         commands with buckets that are Command ArrayLists.
 */
public class Parser implements ParserAPI {

	private String syntaxPath = "resources/languages/Syntax";
	private String language = "English";
	private String ERROR_MATCH = "No Matching Commands";
	private String COMMENT_MATCH = "Comment";
	private String CONSTANT_MATCH = "Constant";
	private String VARIABLE_MATCH = "Variable";
	private String COMMAND_MATCH = "Command";
	private String LIST_START_MATCH = "ListStart";
	private String LIST_END_MATCH = "ListEnd";


	private Controller controller;

	private ObservableList<String> historyList;
	private CommandMap stringToCommandMap;
	private Stack<String> commands;
	private Stack<Double> arguments;
	private List<Entry<String, Pattern>> mySymbols;
	private StateStorage stateStorage;

	public Parser(Controller c) {
		controller = c;
		historyList = FXCollections.observableList(new ArrayList<String>());
		this.createPatternMap();
		arguments = new Stack<Double>();
		commands = new Stack<String>();
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
	public void parse(String input) {
		historyList.add(0, input);
		internalParse(input);
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
	
	private double internalParse(String input) {
		double result = 0.0;
		List<String> tokens = Arrays.asList(input.split("\\s+"));
		result = preOrderEvaluation(tokens);
		if(!commands.isEmpty()) {
			result = inputToCommands(commands, arguments);
		}
		return result;
	}
	
	private double preOrderEvaluation(List<String> tokens) {
		if(tokens.size() == 1 && isConstant(tokens.get(0))) {
			return Double.parseDouble(tokens.get(0));
		}
		
		double mostRecentReturnValue = 0.0;
		if (tokens != null) {
			int arrayLength = tokens.size();
			for (int i = 0; i < arrayLength; i++) {
				String token = tokens.get(i);
				
				if(token.equals("if")) {
					i = handleIf(i, tokens);
				} else if(token.equals("ifelse")) {
					i = handleIfElse(i, tokens);
				} else if(token.equals("repeat")) {
					i = handleRepeat(i, tokens);
				} else if(token.equals("dotimes")) {
					i = handleDoTimes(i, tokens);
				}
				
				if(isComment(token)) {
					//DO NOTHING
				} else if(isConstant(token)) {
					this.addArgumentAsDouble(token);
				} else if(isVariable(token)) {
					int varIndex = stateStorage.getVariableIndex(new Variable(token.replaceAll("[:]", ""), 0.0));
					if(varIndex != -1) {
						Variable var = stateStorage.getVariables().get(varIndex);
						this.addArgumentAsDouble(var.getValue());
					}
				} else if(isText(token)) {
					if (isBuiltInCommand(token)) {
						if (!commands.isEmpty()
								&& (stringToCommandMap.get(commands.peek()).numParameters() <= arguments.size())) {
							mostRecentReturnValue = inputToCommands(commands, arguments);
						}
						commands.push(token);
					} 
				} else if(isError(token)) {
					controller.getView().showMessage(ERROR_MATCH + " " + token);
				}
			}
		}
		return mostRecentReturnValue;
	}
	
	private double inputToCommands(Stack<String> commandStack, Stack<Double> argumentStack) {
		double result = 0.0;
		int size = commandStack.size();
		for (int i = 0; i < size; i++) {
			String s = commandStack.pop();
			Command toExecute = stringToCommandMap.get(s);
			if (argumentStack.size() == 0 && toExecute.numParameters() != 0) {
				commandStack.push(s);
				continue;
			}
			if ((stringToCommandMap.get(s).numParameters() <= arguments.size())) {
				Command newInstance;
				double evaluation = 0.0;
			
				try {
					newInstance = toExecute.getClass().newInstance();
					List<Double> params = createArgumentList(argumentStack, toExecute.numParameters());
					newInstance.initialize(params, controller);
					evaluation = newInstance.getReturnValue();
					controller.handleCommand(newInstance);
				} catch (InstantiationException | IllegalAccessException e) {
					controller.getView().showMessage("Command not found at runtime.");			
				}
				
				if (!(commandStack.size() == 0)) {
					argumentStack.push(evaluation);
					continue;
				}
				controller.print(Double.toString(evaluation));
				result = evaluation;
			} else {
				commandStack.push(s);
			}
		}
		return result;
	}
	
	private void addArgumentAsDouble(String token) {
		if(!commands.isEmpty()) {
			arguments.push(Double.parseDouble(token));
		}
	}
	
	private boolean isBuiltInCommand(String token) {
		return stringToCommandMap.keySet().contains(token);
	}
	
	private boolean isError(String token) {
		return checkArgument(token).equals(ERROR_MATCH);
	}
	
	private boolean isComment(String token) {
		return checkArgument(token).equals(COMMENT_MATCH);
	}
	
	private boolean isConstant(String token) {
		return checkArgument(token).equals(CONSTANT_MATCH);
	}
	
	private boolean isVariable(String token) {
		return checkArgument(token).equals(VARIABLE_MATCH);
	}
	
	private boolean isText(String token) {
		return checkArgument(token).equals(COMMAND_MATCH);
	}
	
	private boolean isListStart(String token) {
		return checkArgument(token).equals(LIST_START_MATCH);
	}
	
	private boolean isListEnd(String token) {
		return checkArgument(token).equals(LIST_END_MATCH);
	}
	
	private int handleIf(int index, List<String> tokens) {
		index = index + 1;
		String expression = "";
		while(index < tokens.size() && !isListStart(tokens.get(index))) {
			expression += " " + tokens.get(index);
			index++;
		}
		
		double result = internalParse(expression.trim());
		
		String commands = "";
		index = index + 1;
		while(index < tokens.size() && !isListEnd(tokens.get(index))) {
			commands += tokens.get(index) + " ";
			index++;
		}
		
		if(result != 0.0) {
			internalParse(commands.trim());
		} 
		return index;
	} 
	
	private int handleIfElse(int index, List<String> tokens) {
		index = index + 1;
		String expression = "";
		while(index < tokens.size() && !isListStart(tokens.get(index))) {
			expression += " " + tokens.get(index);
			index++;
		}
		
		double result = internalParse(expression.trim());
		
		String commandsTrue = "";
		index = index + 1;
		while(index < tokens.size() && !isListEnd(tokens.get(index))) {
			commandsTrue += tokens.get(index) + " ";
			index++;
		}
		
		if(result != 0.0) {
			internalParse(commandsTrue.trim());
		}
		
		String commandsFalse = "";
		index = index + 2;
		while(index < tokens.size() && !isListEnd(tokens.get(index))) {
			commandsFalse += tokens.get(index) + " ";
			index++;
		}
		
		if(result == 0.0) {
			internalParse(commandsFalse.trim());
		}
		return index;
	}
		
	private int handleRepeat(int index, List<String> tokens) {
		index = index + 1;
		String expression = "";
		while(index < tokens.size() && !isListStart(tokens.get(index))) {
			expression += " " + tokens.get(index);
			index++;
		}
		
		double numRepeats = internalParse(expression.trim());
		
		String commands = "";
		index = index + 1;
		while(index < tokens.size() && !isListEnd(tokens.get(index))) {
			commands += tokens.get(index) + " ";
			index++;
		}
		
		for(int k = 1; k <= numRepeats; k++) {
			stateStorage.setVariable(new Variable("repcount", k));
			internalParse(commands.trim());
		} 
		return index;
	}
	
	private int handleDoTimes(int index, List<String> tokens) {
		index += 2;
		String variableName = tokens.get(index).replaceAll("[:]", "");
		int variableIndex = stateStorage.getVariableIndex(new Variable(variableName, 0.0));
		double variableValue = Double.parseDouble(stateStorage.getVariables().get(variableIndex).getValue());
		
		index += 1;
		double limit = Double.parseDouble(tokens.get(index));
		
		index += 3;
		String commands = "";
		while(index < tokens.size() && !isListEnd(tokens.get(index))) {
			commands += tokens.get(index) + " ";
			index++;
		}
		
		for(double k = variableValue; k <= limit; k++) {
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

	private boolean match(String text, Pattern regex) {
		return regex.matcher(text).matches();
	}

	private String checkArgument(String text) {
		final String ERROR = ERROR_MATCH;
		for (Entry<String, Pattern> e : mySymbols) {
			if (match(text, e.getValue())) {
				return e.getKey();
			}
		}
		return ERROR;
	}
	
	private void createPatternMap() {
		ResourceBundle resources = ResourceBundle.getBundle(syntaxPath);
		Enumeration<String> iter = resources.getKeys();
		mySymbols = new ArrayList<Entry<String, Pattern>>();
	    while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            mySymbols.add(new SimpleEntry<String, Pattern>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
	    }
	}
}
