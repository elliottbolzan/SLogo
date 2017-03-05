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
		double distance = distance(new double[] {getParameter(0).getDouble(), getParameter(1).getDouble()}, getController().getTurtle());
		Point loc = new Point(getParameter(0).getDouble(), getParameter(1).getDouble());
		getController().moveTo(loc);
		return new Argument(distance);
	}
}
