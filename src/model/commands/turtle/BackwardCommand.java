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
		Point loc = super.endLocation(-1 * getParameter(0).getDouble(), getController().getTurtle());
		getController().moveTo(loc);
		return getParameter(0);
	}
}
