package model.parser.nodes;

import model.commands.Command;
import model.commands.Commands;
import model.commands.math.MathCommand;
import model.parser.Argument;
import model.parser.Input;
import model.parser.TreeParser;
import model.parser.tokenize.Token;
import model.parser.tokenize.Tokenize;

public class GroupNode extends Node {

	private String newExpression = "";

	public GroupNode(TreeParser parser, Node parent, Input input, Commands commands) {
		super(parser, parent);
		String commandName = input.getWords().get(input.getIndex());
		Command command = commands.get(commandName);
		if (command instanceof MathCommand && command.numParameters() == 2) {
			arithmeticGroup(parser, parent, input, command, commandName);
		} else {
			generalGroup(parser, parent, input, command, commandName);
		}
	}

	@Override
	public Argument evaluate() {
		Argument result = new Argument();
		for (Node child : getChildren()) {
			result = child.evaluate();
		}
		return result;
	}

	public String getExpression() {
		return newExpression;
	}

	private void addNode(TreeParser parser) {
		try {
			getChildren().addAll(parser.parseInternal(newExpression.trim()).getChildren());
		} catch (Exception e) {
		}
	}

	private void arithmeticGroup(TreeParser parser, Node parent, Input input, Command command, String commandName) {
		input.addToIndex(1);
		int openParentheses = 0;
		int arguments = -1;
		while (openParentheses >= 0) {
			String word = input.getWords().get(input.getIndex());
			Token token = new Tokenize().typeOf(word);
			if (token == Token.GROUP_START) {
				openParentheses++;
			}
			else if (token == Token.GROUP_END) {
				openParentheses = -1;
				break;
			}
			arguments += 1;
			input.addToIndex(1);
			newExpression += word + " ";
		}
		for (int i = 0; i < arguments; i++) {
			newExpression = commandName + " " + newExpression;
		}
		addNode(parser);
	}

	private void generalGroup(TreeParser parser, Node parent, Input input, Command command, String commandName) {
		input.addToIndex(1);
		int openParentheses = 0;
		int parameters = command.numParameters();
		while (openParentheses >= 0) {
			String word = input.getWords().get(input.getIndex());
			Token token = new Tokenize().typeOf(word);
			if (token == Token.GROUP_START) {
				openParentheses++;
			}
			if (token == Token.GROUP_END) {
				openParentheses = -1;
				break;
			}
			newExpression = commandName + " ";
			for (int i = 0; i < parameters; i++) {
				word = input.getWords().get(input.getIndex());
				input.addToIndex(1);
				newExpression += word + " ";
			}
			if (openParentheses != -1) {
				addNode(parser);
			}
		}
	}

}
