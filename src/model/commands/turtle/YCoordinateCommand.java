package model.commands.turtle;

import model.parser.Argument;

public class YCoordinateCommand extends TurtleCommand {

	public YCoordinateCommand() {
		super();
	}
	
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument execute() {
		return new Argument(getController().getTurtleManager().getCurrentTurtle().getDestination().getY());
	}
}
