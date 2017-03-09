package model.parser.nodes;
import model.commands.Command;
import model.commands.Commands;
import model.parser.Argument;
import model.parser.Input;
import model.parser.TreeParser;
import model.parser.tokenize.Token;
import model.parser.tokenize.Tokenize;

public class GroupNode extends Node {
	private String newExpression;
	private int sum;
	private int numParam;
	
	public GroupNode(TreeParser parser, Node parent, Input input, Commands commands) {
		super(parser, parent);
		Node child = null;
		String cmd = input.getWords().get(1);
		child = commands.get(cmd);
		numParam = ((Command)child).numParameters();
		input.addToIndex(2);
		System.out.println(numParam);
		
		while(true){
			newExpression = cmd+" ";
			for(int i=0; i<numParam; i++){
				String word = input.getWords().get(input.getIndex());
				Token token = Tokenize.determineType(word);
				if(token == Token.GROUP_END) break;
				input.addToIndex(1);
				newExpression+=word+ " ";
			}
			System.out.println(newExpression);
			addNode(parser);
		}
//		super(parser, parent);
//		int openBrackets = 0;
//		newExpression = input.getWords().get(1) + " ";
//		
//		System.out.println(input.getWords());
//		while (openBrackets >= 0) {
//			String word = input.getWords().get(input.getIndex());
//			Token token = Tokenize.determineType(word);
//			input.addToIndex(1);
//			
//			
//			System.out.println(newExpression);
//			
//			if (token == Token.GROUP_START) {
//				openBrackets++;
//			}
//			else if(token == Token.CONSTANT){
//				sum+=Integer.parseInt(word);
//			}
//			else if (token == Token.GROUP_END) {
//				openBrackets--;
//				if (openBrackets == -1) {
//					newExpression += sum + " ";
//					break;
//				}
//			}	
//		}
//		addNode(parser);
	}
	
	private void addNode(TreeParser parser){
		try {
			getChildren().addAll(parser.parseInternal(newExpression.trim()).getChildren());
		} catch (Exception e) {
		}
	}

	@Override
	public Argument evaluate() {
		Argument result = new Argument();
		for (Node child: getChildren()) {
			result = child.evaluate();
		}
		return result;
	}
	
	public String getExpression(){
		return newExpression;
	}

}
