package model.parser.nodes;

import model.parser.Argument;
import model.parser.TreeParser;

/*
 * Node for all constants.
 */
public class ConstantNode extends Node {
	
	private double value;
	private String string;

	public ConstantNode(TreeParser parser, Node parent, double value) {
		super(parser, parent);
		this.value = value;
	}
	
	public ConstantNode(TreeParser parser, Node parent, String string) {
		super(parser, parent);
		this.string = string;
	}

	@Override
	public Argument evaluate() {
		return new Argument(string, value);
	}

}
