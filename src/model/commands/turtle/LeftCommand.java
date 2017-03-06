package model.commands.turtle;

import model.parser.Argument;

public class LeftCommand extends TurtleCommand {
	
	@Override
	public int numParameters() {
		return 1;
	}
	
	@Override
	public Argument execute() {
		Argument result = getParameter(0);
		getController().turn(result.getDouble());
		return result;
	}
}
