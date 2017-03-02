package model.parse;

import java.util.List;

import model.Variable;
import model.parse.tokens.Identify;
import model.parse.tokens.TokenType;

public class RepeatBlockHandler {

	private Parser myParser;
	
	public RepeatBlockHandler(Parser parser) {
		myParser = parser;
	}
	
	protected int handleRepeat(int index, List<String> tokens) throws NumberFormatException, Exception {
		index = index + 1;
		String expression = "";
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_START)) {
			expression += " " + tokens.get(index);
			index++;
		}

		double numRepeats = myParser.internalParse(expression.trim());

		String commands = "";
		index = index + 1;
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_END)) {
			commands += tokens.get(index) + " ";
			index++;
		}

		for (int k = 1; k <= numRepeats; k++) {
			myParser.getStateStorage().setVariable(new Variable("repcount", k));
			myParser.internalParse(commands.trim());
		}
		return index;
	}
}
