package model.commands.turtle;

import model.parser.Argument;

public class PenUpCommand extends RepeatableTurtleCommand {

	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument innerExecute() {
		getTurtle().setPenDown(false);
		return new Argument(0);
	}
}
