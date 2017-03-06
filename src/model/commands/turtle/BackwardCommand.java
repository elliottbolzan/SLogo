package model.commands.turtle;

import model.parser.Argument;
import utils.Point;

public class BackwardCommand extends TurtleCommand {
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		Argument result = getParameter(0);
		Point loc = endLocation(-result.getDouble(), getController().getTurtle());
		getController().moveTo(loc);
		return result;
	}
}
