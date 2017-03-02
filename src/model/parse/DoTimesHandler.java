package model.parse;

import java.util.List;

import model.Variable;
import model.parse.tokens.Identify;
import model.parse.tokens.TokenType;
import utils.BadInputException;

public class DoTimesHandler {

	private Parser myParser;
	
	public DoTimesHandler(Parser parser) {
		myParser = parser;
	}

	protected int handleDoTimes(int index, List<String> tokens) throws BadInputException {
		index += 2;
		String variableName = tokens.get(index).replaceAll("[:]", "");
		int variableIndex = myParser.getStateStorage().getVariableIndex(new Variable(variableName, 0.0));
		double variableValue = Double.parseDouble(myParser.getStateStorage().getVariables().get(variableIndex).getValue());

		index += 1;
		double limit = Double.parseDouble(tokens.get(index));

		index += 3;
		String commands = "";
		while (index < tokens.size() && !(Identify.determineType(tokens.get(index)) == TokenType.LIST_END)) {
			commands += tokens.get(index) + " ";
			index++;
		}

		for (double k = variableValue; k <= limit; k++) {
			myParser.getStateStorage().setVariable(new Variable(variableName, k));
			myParser.internalParse(commands.trim());
		}
		return index;
	}
}
