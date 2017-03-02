package model.parse;

import java.util.List;

import model.Variable;

public class RepeatBlockHandler {

	private Parser myParser;
	
	public RepeatBlockHandler(Parser parser) {
		myParser = parser;
	}
	
	/*
	protected int handleRepeat(int index, List<String> tokens) {
		index = index + 1;
		String expression = "";
		while (index < tokens.size() && !isListStart(tokens.get(index))) {
			expression += " " + tokens.get(index);
			index++;
		}

		double numRepeats = myParser.internalParse(expression.trim());

		String commands = "";
		index = index + 1;
		while (index < tokens.size() && !isListEnd(tokens.get(index))) {
			commands += tokens.get(index) + " ";
			index++;
		}

		for (int k = 1; k <= numRepeats; k++) {
			stateStorage.setVariable(new Variable("repcount", k));
			myParser.internalParse(commands.trim());
		}
		return index;
	}*/
}
