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

		int openParantheses = 0;
		while(openParantheses >= 0){
			String word = input.getWords().get(input.getIndex());
			String cmd = "";
			Token token = Tokenize.determineType(word);
//			System.out.println("1: "+word);
//			input.addToIndex(1);
//			word = input.getWords().get(input.getIndex());
//			System.out.println("2: "+word);
//			input.addToIndex(1);
//			word = input.getWords().get(input.getIndex());
//			System.out.println("3: "+word);			
//			input.addToIndex(1);
//			word = input.getWords().get(input.getIndex());
//			System.out.println("4: "+word);
			
			//if(token == Token.GROUP_START){
				openParantheses++;
				Node child = null;
				cmd = input.getWords().get(input.getIndex());
				System.out.println(cmd);
				child = commands.get(cmd);
				numParam = ((Command)child).numParameters();
			//}
			input.addToIndex(1);
			newExpression = cmd+" ";
			for(int i=0; i<numParam; i++){
				word = input.getWords().get(input.getIndex());
				if(token == Token.GROUP_END) {
					openParantheses = -1;
					break;
				}
				input.addToIndex(1);
				newExpression+=word+ " ";
			}
			//System.out.println(newExpression);
			addNode(parser);
		}
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
