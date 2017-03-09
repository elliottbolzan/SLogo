package model.commands.turtle;

import model.parser.Argument;

public class XCoordinateCommand extends TurtleCommand {

	public XCoordinateCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument execute() {
		return new Argument(getController().getTurtleManager().getCurrentTurtle().getDestination().getX());
	}
}
