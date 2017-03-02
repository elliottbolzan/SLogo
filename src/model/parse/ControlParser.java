package model.parse;

import java.util.List;

public class ControlParser{

private int handleIf(int index, List<String> tokens) {
		index = index + 1;
		String expression = "";
		while (index < tokens.size() && !isListStart(tokens.get(index))) {
			expression += " " + tokens.get(index);
			index++;
		}

		double result = internalParse(expression.trim());

		String commands = "";
		index = index + 1;
		while (index < tokens.size() && !isListEnd(tokens.get(index))) {
			commands += tokens.get(index) + " ";
			index++;
		}

		if (result != 0.0) {
			internalParse(commands.trim());
		}
		return index;
	}

	private int handleIfElse(int index, List<String> tokens) {
		index = index + 1;
		String expression = "";
		while (index < tokens.size() && !isListStart(tokens.get(index))) {
			expression += " " + tokens.get(index);
			index++;
		}

		double result = internalParse(expression.trim());

		String commandsTrue = "";
		index = index + 1;
		while (index < tokens.size() && !isListEnd(tokens.get(index))) {
			commandsTrue += tokens.get(index) + " ";
			index++;
		}

		if (result != 0.0) {
			internalParse(commandsTrue.trim());
		}

		String commandsFalse = "";
		index = index + 2;
		while (index < tokens.size() && !isListEnd(tokens.get(index))) {
			commandsFalse += tokens.get(index) + " ";
			index++;
		}

		if (result == 0.0) {
			internalParse(commandsFalse.trim());
		}
		return index;
	}

	private int handleTo(int index, List<String> tokens) {
		index = index + 1;

		String expression = tokens.get(index);

		index += 2;

		internalParse(expression.trim());

		// added to text our command name

		while (index < tokens.size() && !isListEnd(tokens.get(index))) {
			expression += " " + tokens.get(index);
			index++;
		}
		
		internalParse(expression.trim());

		index = index + 2;

		expression = "";

		while (index < tokens.size() && !isListEnd(tokens.get(index))) {
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
		
	private int handleRepeat(int index, List<String> tokens) {
		index = index + 1;
		String expression = "";
		while (index < tokens.size() && !isListStart(tokens.get(index))) {
			expression += " " + tokens.get(index);
			index++;
		}

		double numRepeats = internalParse(expression.trim());

		String commands = "";
		index = index + 1;
		while (index < tokens.size() && !isListEnd(tokens.get(index))) {
			commands += tokens.get(index) + " ";
			index++;
		}

		for (int k = 1; k <= numRepeats; k++) {
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
		while (index < tokens.size() && !isListEnd(tokens.get(index))) {
			commands += tokens.get(index) + " ";
			index++;
		}

		for (double k = variableValue; k <= limit; k++) {
			stateStorage.setVariable(new Variable(variableName, k));
			internalParse(commands.trim());
		}
		return index;
	}

	private int handleForLoop(int index, List<String> tokens) {
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
		while (index < tokens.size() && !isListEnd(tokens.get(index))) {
			commands += tokens.get(index) + " ";
			index++;
		}

		for (double k = variableStart; k <= variableEnd; k += variableIncrement) {
			stateStorage.setVariable(new Variable(variableName, k));
			internalParse(commands.trim());
		}
		return index;
	}
}