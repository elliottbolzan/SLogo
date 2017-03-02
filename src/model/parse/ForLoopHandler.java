package model.parse;

import java.util.List;

import model.Variable;
import model.parse.tokens.Identify;
import model.parse.tokens.TokenType;

public class ForLoopHandler {

	private Parser myParser;
	
	public ForLoopHandler(Parser parser) {
		myParser = parser;
	}
	
	protected int handleForLoop(int index, List<String> tokens) throws Exception {
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
			myParser.getStateStorage().setVariable(new Variable(variableName, k));
			myParser.internalParse(commands.trim());
		}
		return index;
	}
}
