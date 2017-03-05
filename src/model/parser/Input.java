package model.parser;

import java.util.List;

import model.parser.nodes.Node;

public class Input {

	private Node node;
	private int index;
	private List<String> words;

	public Input(Node node, int index, List<String> words) {
		this.node = node;
		this.index = index;
		this.words = words;
	}

	public Node getNode() {
		return node;
	}

	public int getIndex() {
		return index;
	}

	public List<String> getWords() {
		return words;
	}
	
	public void addToIndex(int i) {
		index += i;
	}
	
	public void setIndex(int i) {
		index = i;
	}

}
