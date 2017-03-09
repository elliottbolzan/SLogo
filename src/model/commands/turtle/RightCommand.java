package model.commands.turtle;

import model.parser.Argument;

public class RightCommand extends RepeatableTurtleCommand {

	@Override
	protected int internalNumParameters() {
		return 1;
	}
	
	@Override
	protected Argument innerExecute() {
		Argument result = getParameter(0);
		getTurtle().turn(-result.getDouble());
		return result;
	}
}
