package model.parse;

import java.util.List;

public class ControlBlockHandler {

	private Parser myParser;
	
	public ControlBlockHandler(Parser parser) {
		myParser = parser;
	}

	private int handleIf(int index, List<String> tokens) {
		index = index + 1;
		String expression = "";
		while (index < tokens.size() && !isListStart(tokens.get(index))) {
			expression += " " + tokens.get(index);
			index++;
		}

		double result = myParser.internalParse(expression.trim());

		String commands = "";
		index = index + 1;
		while (index < tokens.size() && !isListEnd(tokens.get(index))) {
			commands += tokens.get(index) + " ";
			index++;
		}

		if (result != 0.0) {
			myParser.internalParse(commands.trim());
		}
		return index;
	}
	
}
