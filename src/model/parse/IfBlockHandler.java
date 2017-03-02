package model.parse;

import java.util.List;
import model.parse.tokens.TokenType;
import utils.BadInputException;
import model.parse.tokens.Identify;

public class IfBlockHandler {

	private Parser myParser;
	
	public IfBlockHandler(Parser parser) {
		myParser = parser;
	}

	protected int handleIf(int index, List<String> tokens) throws BadInputException {
		index = index + 1;
		String expression = "";
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_START)) {
			expression += " " + tokens.get(index);
			index++;
		}

		double result = myParser.internalParse(expression.trim());

		String commands = "";
		index = index + 1;
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_END)) {
			commands += tokens.get(index) + " ";
			index++;
		}

		if (result != 0.0) {
			myParser.internalParse(commands.trim());
		}
		return index;
	}
}
