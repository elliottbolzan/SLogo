package model.commands.display;

import model.commands.turtle.TurtleCommand;
import model.parser.Argument;

public class GetPenColorCommand extends TurtleCommand {

	@Override
	protected int internalNumParameters() {
		return 0;
	}

	@Override
	protected Argument execute() {
		return new Argument(getTurtle().getPenColorIndex());
	}
	
}