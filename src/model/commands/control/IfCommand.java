package model.commands.control;

import model.commands.Command;
import model.parser.Argument;

public class IfCommand extends Command {
	
	@Override
	protected int internalNumParameters() {
		return 2;
	}

	@Override
	protected Argument execute(){
		Argument result = new Argument();
		if (getChildren().get(0).evaluate().getDouble() != 0) {
			result = getChildren().get(1).evaluate();
		}
		return result;
	}

}
