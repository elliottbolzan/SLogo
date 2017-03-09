package model.parser.nodes;

import model.commands.Command;
import model.commands.Commands;
import model.parser.Argument;
import model.parser.Input;
import model.parser.TreeParser;
import model.parser.tokenize.Token;
import model.parser.tokenize.Tokenize;

public class GroupNode extends Node {
	private String newExpression;
	private int numParam;

	public GroupNode(TreeParser parser, Node parent, Input input, Commands commands) {
		super(parser, parent);

		String cmd = input.getWords().get(input.getIndex());
		if (cmd.equals("")) {
			input.addToIndex(1);
			input.getWords().get(input.getIndex());
		}
		System.out.println(cmd);
		input.addToIndex(1);
		Command child = commands.get(cmd);
		numParam = child.numParameters();

		int openParantheses = 0;
		while (openParantheses >= 0) {
			String word = input.getWords().get(input.getIndex());
			Token token = new Tokenize().typeOf(word);

			if (token == Token.GROUP_START) {
				openParantheses++;
			}

			newExpression = cmd + " ";
			for (int i = 0; i < numParam; i++) {
				word = input.getWords().get(input.getIndex());
				if (token == Token.GROUP_END) {
					openParantheses = -1;
					break;
				}
				input.addToIndex(1);
				newExpression += word + " ";
			}
			if (openParantheses != -1) {
				addNode(parser);
			}
		}
	}

	private void addNode(TreeParser parser) {
		try {
			getChildren().addAll(parser.parseInternal(newExpression.trim()).getChildren());
		} catch (Exception e) {
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

}
