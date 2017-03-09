package model.commands.turtle;

import model.parser.Argument;

public class BackwardCommand extends TurtleCommand {
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		Argument result = getParameter(0);
		getController().getTurtleManager().verticalMove(-result.getDouble());
		return result;
	}
}
