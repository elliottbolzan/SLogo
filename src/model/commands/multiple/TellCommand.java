package model.commands.multiple;

import model.commands.Command;
import model.parser.Argument;
import model.parser.nodes.ListNode;

public class TellCommand extends Command{
	
	public TellCommand(){
		super();
	}

	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		Argument result = new Argument();
		result = getParameter(0);
		
		ListNode temp = (ListNode) getChildren().get(0);
		String expression = temp.getExpression();
		String[] split = expression.split("\\s+");
		Integer[] addTurtles = new Integer[split.length];
		for(int i=0; i<split.length; i++){
			addTurtles[i] = Integer.parseInt(split[i]);
			getController().getTurtle(i);
		}
		getController().getActiveTurtles().setTurtles(addTurtles);
		
		System.out.println(result.getDouble());
		return result;
	}
	
}