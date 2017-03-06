package model.parser;

import java.util.ArrayList;
import java.util.Arrays;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.State;
import model.Variable;
import model.commands.Command;
import model.commands.Commands;
import model.parser.nodes.ConstantNode;
import model.parser.nodes.ListNode;
import model.parser.nodes.Node;
import model.parser.nodes.RootNode;
import model.parser.nodes.VariableNode;
import model.parser.tokenize.Token;
import model.parser.tokenize.Tokenize;
import utils.BadInputException;

public class TreeParser implements ParserAPI {

	private String language = "English";
	private Controller controller;
	private ObservableList<String> historyList;
	private Commands commands;
	private State state;

	public TreeParser(Controller controller) {
		this.controller = controller;
		historyList = FXCollections.observableList(new ArrayList<String>());
		commands = new Commands();
		state = new State();
	}

	public void setLanguage(String language) {
		this.language = language;
		commands.updateLanguage(language);
	}

	public String getLanguage() {
		return language;
	}

	public State getState() {
		return state;
	}

	public ObservableList<Variable> getVariables() {
		return state.getVariables();
	}

	public ObservableList<String> getUserDefinedCommands() {
		return state.getUserDefinedCommands();
	}

	@Override
	public ObservableList<String> getHistory() {
		return historyList;
	}

	@Override
	public String getPreviousCommand(int k) {
		return historyList.get(0);
	}

	private void printTree(Node node, String spacing) {
		System.out.println(spacing + node);
		spacing += " ";
		for (Node child : node.getChildren()) {
			printTree(child, spacing);
		}
	}

	@Override
	public Node parse(String input) throws BadInputException {
		historyList.add(0, input);
		Node root = parseInternal(input);
		printTree(root, "");
		controller.print(String.valueOf(root.evaluate().getDouble()));
		return root;
	}

	public Node parseInternal(String input) throws BadInputException {
		return createTree(input);
	}

	private Node createTree(String string) {
		ArrayList<String> words = new ArrayList<String>(Arrays.asList(string.split("\\s+")));
		Node node = new RootNode(this, null);
		Input input = new Input(node, 0, words);
		while (input.getIndex() < input.getWords().size()) {
			input.setIndex(createTree(input).getIndex());
		}
		return input.getNode();
	}

	private Input createTree(Input input) {
		String word = input.getWords().get(input.getIndex());
		try {
			Token token = Tokenize.determineType(word);
			Node node = input.getNode();
			Node child = null;
			input.addToIndex(1);
			if (token == Token.CONSTANT) {
				child = new ConstantNode(this, node, Double.parseDouble(word));
			} else if (token == Token.VARIABLE) {
				child = new VariableNode(this, node, word.replaceAll(":", ""));
			} else if (token == Token.COMMAND) {
				child = commands.get(word);
				// If child is null, your command is probably misnamed.
				((Command) child).setup(controller, state);
				for (int i = 0; i < ((Command) child).numParameters(); i++) {
					input = createTree(new Input(child, input.getIndex(), input.getWords()));
				}
			} else if (token == Token.LIST_START) {
				child = new ListNode(this, node, input);
			}
			// Work on comments.
			// In UI, implement new Console behavior: don't strip newlines. Use newlines to parse comments.
			node.addChild(child);
			return new Input(child, input.getIndex(), input.getWords());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}