package model.commands.control;

import model.commands.Command;
import model.parser.Argument;

public class IfElseCommand extends Command {

	@Override
	protected int internalNumParameters() {
		return 3;
	}

	@Override
	protected Argument execute(){
		Argument result = new Argument();
		if (getChildren().get(0).evaluate().getDouble() != 0) {
			result = getChildren().get(1).evaluate();
		}
		else {
			result = getChildren().get(2).evaluate();
		}
		return result;
	}

}
