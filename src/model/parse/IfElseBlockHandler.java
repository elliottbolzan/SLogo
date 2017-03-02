package model.parse;

import java.util.List;

import model.parse.tokens.Identify;
import model.parse.tokens.TokenType;

public class IfElseBlockHandler {

	private Parser myParser;
	
	public IfElseBlockHandler(Parser parser) {
		myParser = parser;
	}

	protected int handleIfElse(int index, List<String> tokens) throws Exception {
		index = index + 1;
		String expression = "";
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_START)) {
			expression += " " + tokens.get(index);
			index++;
		}

		double result = myParser.internalParse(expression.trim());

		String commandsTrue = "";
		index = index + 1;
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_END)) {
			commandsTrue += tokens.get(index) + " ";
			index++;
		}

		if (result != 0.0) {
			myParser.internalParse(commandsTrue.trim());
		}

		String commandsFalse = "";
		index = index + 2;
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_END)) {
			commandsFalse += tokens.get(index) + " ";
			index++;
		}

		if (result == 0.0) {
			myParser.internalParse(commandsFalse.trim());
		}
		return index;
	}
}
