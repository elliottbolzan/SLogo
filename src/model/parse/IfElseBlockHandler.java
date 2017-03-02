package model.parse;

import java.util.List;

public class IfElseBlockHandler {

	private Parser myParser;
	
	public IfElseBlockHandler(Parser parser) {
		myParser = parser;
	}

	/*
	protected int handleIfElse(int index, List<String> tokens) {
		index = index + 1;
		String expression = "";
		while (index < tokens.size() && !isListStart(tokens.get(index))) {
			expression += " " + tokens.get(index);
			index++;
		}

		double result = myParser.internalParse(expression.trim());

		String commandsTrue = "";
		index = index + 1;
		while (index < tokens.size() && !isListEnd(tokens.get(index))) {
			commandsTrue += tokens.get(index) + " ";
			index++;
		}

		if (result != 0.0) {
			myParser.internalParse(commandsTrue.trim());
		}

		String commandsFalse = "";
		index = index + 2;
		while (index < tokens.size() && !isListEnd(tokens.get(index))) {
			commandsFalse += tokens.get(index) + " ";
			index++;
		}

		if (result == 0.0) {
			myParser.internalParse(commandsFalse.trim());
		}
		return index;
	}*/
}
