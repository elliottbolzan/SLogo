package model.commands.control;

import model.commands.Command;
import model.parser.Argument;
import utils.BadInputException;

public class IfCommand extends Command {
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument execute() throws BadInputException {
		Argument result = new Argument();
		if (getChildren().get(0).evaluate().getDouble() != 0) {
			result = getChildren().get(1).evaluate();
		}
		return result;
	}

}
