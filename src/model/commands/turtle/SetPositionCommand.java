package model.commands.turtle;

import model.parser.Argument;
import utils.Point;

public class SetPositionCommand extends RepeatableTurtleCommand {

	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument innerExecute() {
		double xCoordinate = getParameter(0).getDouble();
		double yCoordinate = getParameter(1).getDouble();
		double distance = distance(new Point(xCoordinate, yCoordinate), getTurtle().getDestination());
		getTurtle().moveTo(new Point(xCoordinate, yCoordinate));
		return new Argument(distance);
	}
}
