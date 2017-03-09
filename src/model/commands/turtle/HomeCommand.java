package model.commands.turtle;

import model.parser.Argument;
import utils.Point;

public class HomeCommand extends RepeatableTurtleCommand {

	@Override
	protected int internalNumParameters() {
		return 0;
	}

	@Override
	protected Argument innerExecute() {
		Argument distance = new Argument(distance(new Point(0, 0), getTurtle().getDestination()));
		getTurtle().moveTo(new Point(0, 0));
		return distance;
	}
}
