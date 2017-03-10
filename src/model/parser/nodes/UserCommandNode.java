package model.parser.nodes;

import model.commands.Command;
import model.parser.Argument;

public class UserCommandNode extends Command{

	@Override
	public Argument evaluate() {
		return new Argument();
	}

	@Override
	protected int internalNumParameters() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	protected Argument execute() {
		System.out.println("called");
		Argument result = new Argument();
	    //getParameter(1);
	    getChildren().get(1).evaluate();
		return result;
	}
}
