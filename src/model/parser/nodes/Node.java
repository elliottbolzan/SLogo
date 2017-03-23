package model.parser.nodes;

import java.util.ArrayList;
import java.util.List;

import model.parser.Argument;
import model.parser.TreeParser;

/**
 * @author Elliott Bolzan
 *
 *         The basic unit of the Tree formed by the TreeParser. The Node is
 *         extended by the Command, the ConstantNode, the ListNode, etc. Every
 *         Node has an evaluate() method.
 */
public abstract class Node {

	private List<Node> children;
	private TreeParser parser;

	public Node(TreeParser parser, Node parent) {
		children = new ArrayList<Node>();
		this.parser = parser;
	}

	protected Node() {
		this(null, null);
	}

	protected TreeParser getParser() {
		return parser;
	}

	public void setParser(TreeParser parser) {
		this.parser = parser;
	}

	public void addChild(Node node) {
		children.add(node);
	}

	public List<Node> getChildren() {
		return children;
	}

	protected Argument getParameter(int k) {
		return children.get(k).evaluate();
	}

	public abstract Argument evaluate();

}
