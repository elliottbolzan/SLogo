package model.commands.turtle;

import model.parser.Argument;
import utils.Point;

public class SetPositionCommand extends TurtleCommand {

	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument execute() {
		Argument xCoordinate = getParameter(0);
		Argument yCoordinate = getParameter(1);
		double distance = distance(new double[] {xCoordinate.getDouble(), yCoordinate.getDouble()}, getController().getTurtleManager().getCurrentTurtle());
		Point loc = new Point(xCoordinate.getDouble(), yCoordinate.getDouble());
		getController().getTurtleManager().moveTo(loc);
		return new Argument(distance);
	}
}
