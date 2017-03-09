package model.commands.turtle;

import model.parser.Argument;

public class BackwardCommand extends RepeatableTurtleCommand {
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument innerExecute() {
		Argument result = getParameter(0);
		getTurtle().moveTo(endLocation(-result.getDouble(), getTurtle()));
		return result;
	}
}
