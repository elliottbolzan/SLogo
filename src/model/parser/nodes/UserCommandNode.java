package model.parser.nodes;

import model.commands.Command;
import model.parser.Argument;

/*
 * Used for user defined commands.
 */
public class UserCommandNode extends Command{

	@Override
	public Argument evaluate() {
		return new Argument();
	}

	@Override
	protected int internalNumParameters() {
		return 2;
	}

	@Override
	protected Argument execute() {
		Argument result = new Argument();
	    getChildren().get(1).evaluate();
		return result;
	}
}
