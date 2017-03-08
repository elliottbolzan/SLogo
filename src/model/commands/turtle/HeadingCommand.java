package model.commands.turtle;

import model.parser.Argument;

public class HeadingCommand extends TurtleCommand {
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		return new Argument(getController().getTurtleManager().getCurrentTurtle().getRotation());
	}
}
