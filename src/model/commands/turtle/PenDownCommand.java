package model.commands.turtle;

import model.parser.Argument;

public class PenDownCommand extends RepeatableTurtleCommand {

	@Override
	protected int internalNumParameters() {
		return 0;
	}

	@Override
	protected Argument innerExecute() {
		getTurtle().setPenDown(true);
		return new Argument(1);
	}
}