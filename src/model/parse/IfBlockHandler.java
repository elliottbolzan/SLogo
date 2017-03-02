package model.parse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.commands.control.UserCommand;

public class IfBlockHandler {

	private Parser myParser;
	
	public IfBlockHandler(Parser parser) {
		myParser = parser;
	}

	/*
	protected int handleIf(int index, List<String> tokens) {
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
	}*/
}
