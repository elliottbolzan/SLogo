package model.commands.turtle;

import model.parser.Argument;

public class PenDownCommand extends RepeatableTurtleCommand {

	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument innerExecute() {
		getTurtle().setPenDown(true);
		return new Argument(1);
	}
}