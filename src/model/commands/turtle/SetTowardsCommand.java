package model.commands.turtle;

import model.parser.Argument;
import view.visualization.Turtle;

public class SetTowardsCommand extends TurtleCommand {
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument execute() {
		Turtle turtle = getController().getTurtleManager().getCurrentTurtle();
		double xdiff = getParameter(0).getDouble() - turtle.getDestination().getX();
		double ydiff = getParameter(1).getDouble() - turtle.getDestination().getY();
		return new Argument(getController().getTurtleManager().towards(Math.atan2(ydiff, xdiff)));
	}
}
