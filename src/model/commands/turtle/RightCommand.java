package model.commands.turtle;

import model.parser.Argument;

public class RightCommand extends RepeatableTurtleCommand {

	@Override
	public int numParameters() {
		return 1;
	}
	
	@Override
	public Argument innerExecute() {
		Argument result = getParameter(0);
		getTurtle().turn(-result.getDouble());
		return result;
	}
}
