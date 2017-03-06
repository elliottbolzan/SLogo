package model.parser.nodes;

import java.util.ArrayList;

import model.parser.Argument;
import model.parser.TreeParser;


public abstract class Node {
	
	private ArrayList<Node> children;
	private Node parent;
	private TreeParser parser;

	public Node(TreeParser parser, Node parent) {
		children = new ArrayList<Node>();
		this.parent = parent;
		this.parser = parser;
	}
	
	public Node() {
		this(null, null);
	}
	
	public TreeParser getParser() {
		return parser;
	}
	
	public void addChild(Node node) {
		children.add(node);
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public ArrayList<Node> getChildren() {
		return children;
	}
	
	public Argument getParameter(int k) {
		return children.get(k).evaluate();
	}
	
	public abstract Argument evaluate();

}
