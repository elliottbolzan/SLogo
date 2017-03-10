package model.commands.turtle;

import model.parser.Argument;

public class PenUpCommand extends RepeatableTurtleCommand {

	@Override
	protected int internalNumParameters() {
		return 0;
	}

	@Override
	protected Argument innerExecute() {
		getTurtle().setPenDown(false);
		return new Argument(0);
	}
}
