package model.commands.turtle;

import model.parser.Argument;

public class HeadingCommand extends TurtleCommand {
	
	@Override
	protected int internalNumParameters() {
		return 0;
	}

	@Override
	protected Argument execute() {
		return new Argument(getTurtle().getRotation());
	}
}
