package model.commands.turtle;

import model.parser.Argument;

public class PenDownCommand extends TurtleCommand {

	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument execute() {
		getController().getTurtleManager().setPenDown(true);
		return new Argument(1);
	}
}