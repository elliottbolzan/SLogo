package model.parser;
import java.util.ArrayList;
import java.util.Arrays;
import controller.Controller;
import javafx.collections.ObservableList;
import model.IndexedColor;
import model.IndexedImage;
import model.State;
import model.Variable;
import model.commands.Command;
import model.commands.Commands;
import model.commands.control.MakeUserInstructionCommand;
import model.parser.nodes.ConstantNode;
import model.parser.nodes.GroupNode;
import model.parser.nodes.ListNode;
import model.parser.nodes.Node;
import model.parser.nodes.RootNode;
import model.parser.nodes.VariableNode;
import model.parser.tokenize.Token;
import model.parser.tokenize.Tokenize;

public class TreeParser {
	
	private String language = "English";
	private Controller controller;
	private Commands commands;
	private ParseHistory parseHistory;
	private State state;
	private boolean prevCmdTo;
	
	public TreeParser(Controller controller) {
		this.controller = controller;
		parseHistory = new ParseHistory();
		commands = new Commands();
		state = new State();
		prevCmdTo = false;
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
	
	public ObservableList<IndexedColor> getColorPalette() {
		return state.getColorPalette();
	}
	
	public ObservableList<IndexedImage> getImagePalette() {
		return state.getImagePalette();
	}
	
	public ObservableList<String> getHistory() {
		return parseHistory.getHistoryList();
	}
	
	public String getPreviousCommand(int k) {
		return parseHistory.getHistoryList().get(0);
	}
	
//	private void printTree(Node node, String spacing) {
//		System.out.println(spacing + node);
//		spacing += " ";
//		final String spaces = spacing;
//		node.getChildren().stream().filter(e -> e != null).forEach(e ->
//		printTree(e, spaces));
//	}
	
	public Node parse(String input, boolean addToHistory) {
		if (addToHistory) {
			parseHistory.addStringToHistory(input);
		}
		input = handleComment(input);
		Node root = parseInternal(input);
		root.evaluate();
		return root;
	}
	
	public Node parseInternal(String input) {
		return startTree(input);
	}
	
	private Node startTree(String string) {
		ArrayList<String> words = new ArrayList<String>();
		words = new ArrayList<String>(Arrays.asList(string.split("\\s+")));
		Node node = new RootNode(this, null);
		Input input = new Input(node, 0, words);
		while (input.getIndex() < input.getWords().size() && input != null) {
			Input here = createTree(input);
			input.setIndex(here.getIndex());
		}
		return input.getNode();
	}
	
	private Input createTree(Input input) {
		String word = input.getWords().get(input.getIndex());
		try {
			Token token = new Tokenize().typeOf(word);
			Node node = input.getNode();
			Node child = null;
			input.addToIndex(1);
			checkUserMadeCommands(word);
			if (token == Token.GROUP_START) {
				child = new GroupNode(this, node, input, commands);
			} else if (token == Token.CONSTANT) {
				child = new ConstantNode(this, node, Double.parseDouble(word));
			} else if (token == Token.VARIABLE) {
				child = new VariableNode(this, node, word.replaceAll(":", ""));
			} else if (token == Token.COMMAND) {
				try {
					child = commands.get(word);
					if (child == null) {
						child = state.getCommand(word);
						child.setParser(this);
					}
					((Command) child).setup(controller, state);
					boolean toCommand = child instanceof MakeUserInstructionCommand;
					if(toCommand) prevCmdTo = true;
					for (int i = 0; i < ((Command) child).numParameters(); i++) {
						input = createTree(new Input(child, input.getIndex(), input.getWords()));
					}
					if (toCommand) child.evaluate();
				} catch (Exception e) {
					child = new ConstantNode(this, node, word);
					if(!prevCmdTo){
						controller.getView().showMessage(String.format(controller.getResources().getString("CommandDoesNotExist"),word));
					}
					else prevCmdTo = false;
				}
			} else if (token == Token.LIST_START) {
				child = new ListNode(this, node, input);
			}
			if(!(child instanceof MakeUserInstructionCommand))	node.addChild(child);
			return new Input(child, input.getIndex(), input.getWords());
		} catch (Exception e) {
			controller.getView().showMessage(controller.getResources().getString("InvalidInput"));
		}
		return null;
	}
	
	private void checkUserMadeCommands(String word){
		if(controller.getUserDefinedCommands().contains(word) && prevCmdTo){
			controller.getView().showMessage((controller.getResources().getString("UserMadeCmdError")));
		}
	}
	
	private String handleComment(String s) {
		ArrayList<String> commentFinder = new ArrayList<>(Arrays.asList(s.split("\\n")));
		StringBuilder sb = new StringBuilder();
		commentFinder.stream().filter(e -> !e.equals("")).filter(e -> e.trim().charAt(0) != '#')
		.forEach(e -> sb.append(e + " "));
		String result = sb.toString();
		if (result.contains("#"))
			controller.getView().showMessage(controller.getResources().getString("CommentError"));
		result.replace(",", "");
		return result;
	}
}