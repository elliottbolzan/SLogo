package model.parser.nodes;

import model.parser.Argument;
import model.parser.TreeParser;

/*
 * Used for user defined variables.
 */
public class VariableNode extends Node {
	
	private String name;

	public VariableNode(TreeParser parser, Node parent, String name) {
		super(parser, parent);
		this.name = name;
	}

	@Override
	public Argument evaluate() {
		return new Argument(name, getParser().getState().getVariableValue(name));
	}

}
