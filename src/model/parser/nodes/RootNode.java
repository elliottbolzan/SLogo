package model.parser.nodes;

import model.parser.Argument;
import model.parser.TreeParser;

public class RootNode extends Node {

	public RootNode(TreeParser parser, Node parent) {
		super(parser, parent);
	}

	@Override
	public Argument evaluate() {
		Argument result =  new Argument();
		for (Node child: getChildren()) {
			result = child.evaluate();
		}
		return result;
	}

}
