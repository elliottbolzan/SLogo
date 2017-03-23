package model.parser;

import java.util.List;

import model.parser.nodes.Node;

/**
 * @author Elliott Bolzan
 *
 *         A class that represents the input to the parsing process. This class
 *         encapsulates the current Node; the List of words that are being
 *         parsed; and the index corresponding to the word that is currently
 *         being parsed.
 */
public class Input {

	private Node node;
	private int index;
	private List<String> words;

	/**
	 * Creates an Input.
	 * @param node the current Node.
	 * @param index the index corresponding to the word that is being parsed.
	 * @param words the words that are being parsed.
	 */
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

	/**
	 * Increment the index by i.
	 * @param i the amount to increment by.
	 */
	public void addToIndex(int i) {
		index += i;
	}

	/**
	 * Set the index to a specific value.
	 * @param i the value to set the index to.
	 */
	public void setIndex(int i) {
		index = i;
	}

}
