package model.parser.nodes;

import model.parser.Argument;
import model.parser.TreeParser;

public class CommentNode extends Node {

	private String comment;

	public CommentNode(TreeParser parser, Node parent, String s) {
		super(parser, parent);
		comment = s;
	}

	public CommentNode() {

	}

	@Override
	public Argument evaluate() {
		return null;
	}

}
