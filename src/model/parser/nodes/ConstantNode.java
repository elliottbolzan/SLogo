package model.parser.nodes;

import model.parser.Argument;
import model.parser.TreeParser;

public class ConstantNode extends Node {
	
	private double value;

	public ConstantNode(TreeParser parser, Node parent, double value) {
		super(parser, parent);
		this.value = value;
	}

	@Override
	public Argument evaluate() {
		return new Argument(value);
	}

}
