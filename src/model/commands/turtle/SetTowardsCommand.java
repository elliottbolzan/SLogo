package model.commands.turtle;

import model.parser.Argument;

public class SetTowardsCommand extends RepeatableTurtleCommand {
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument innerExecute() {
		double xdiff = getParameter(0).getDouble() - getTurtle().getDestination().getX();
		double ydiff = getParameter(1).getDouble() - getTurtle().getDestination().getY();
		double degrees = Math.toDegrees(Math.atan2(ydiff, xdiff)) - getTurtle().getRotation();
		getTurtle().turn(degrees);
		return new Argument(degrees);
	}
}
