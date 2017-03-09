package model.commands.turtle;

import model.parser.Argument;

public class XCoordinateCommand extends TurtleCommand {

	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument execute() {
		return new Argument(getTurtle().getDestination().getX());
	}
}
