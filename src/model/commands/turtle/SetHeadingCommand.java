package model.commands.turtle;

import model.parser.Argument;

public class SetHeadingCommand extends RepeatableTurtleCommand {

	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument innerExecute() {
		double result = getParameter(0).getDouble() - getTurtle().getRotation();
		getTurtle().turn(result);
		return new Argument(result);
	}
}
