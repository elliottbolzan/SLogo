package model.commands.turtle;

import model.parser.Argument;

public class LeftCommand extends TurtleCommand {
	
	@Override
	public int numParameters() {
		return 1;
	}
	
	@Override
	public Argument execute() {
		getController().turn(getParameter(0).getDouble());
		return getParameter(0);
	}
}
