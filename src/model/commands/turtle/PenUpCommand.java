package model.commands.turtle;

import model.parser.Argument;

public class PenUpCommand extends TurtleCommand {

	public PenUpCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument execute() {
		getController().getTurtleManager().setPenDown(false);
		return new Argument(0);
	}
}
