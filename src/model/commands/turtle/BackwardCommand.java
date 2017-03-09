package model.commands.turtle;

import model.parser.Argument;

public class BackwardCommand extends RepeatableTurtleCommand {
	
	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument innerExecute() {
		Argument result = getParameter(0);
		getTurtle().moveTo(endLocation(-result.getDouble(), getTurtle()));
		return result;
	}
}
