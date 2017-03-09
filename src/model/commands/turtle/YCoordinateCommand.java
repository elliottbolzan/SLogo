package model.commands.turtle;

import model.parser.Argument;

public class YCoordinateCommand extends TurtleCommand {
	
	@Override
	protected int internalNumParameters() {
		return 0;
	}

	@Override
	protected Argument execute() {
		return new Argument(getTurtle().getDestination().getY());
	}
}
