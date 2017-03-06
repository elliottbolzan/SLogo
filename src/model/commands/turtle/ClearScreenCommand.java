package model.commands.turtle;

import model.parser.Argument;
import utils.Point;

public class ClearScreenCommand extends TurtleCommand {

	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument execute() {
		double distance = distance(getController().getTurtle().getDestination(), new Point(0, 0));
		getController().moveTo(new Point(0, 0));
		getController().clearDisplay();
		return new Argument(distance);
	}
}
