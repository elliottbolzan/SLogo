package model.parser.nodes;

import model.parser.Argument;
import model.parser.TreeParser;

/*
 * Beginning of a tree. Automatically added as the root on each new line.
 */
public class RootNode extends Node {

	public RootNode(TreeParser parser, Node parent) {
		super(parser, parent);
	}

	@Override
	public Argument evaluate() {
		Argument result =  new Argument();
		for (Node child: getChildren()) {
			if(child != null) result = child.evaluate();
		}
		return result;
	}

}
