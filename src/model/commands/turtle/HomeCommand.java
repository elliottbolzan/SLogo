package model.commands.turtle;

import model.parser.Argument;
import utils.Point;

public class HomeCommand extends RepeatableTurtleCommand {

	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument innerExecute() {
		Argument distance = new Argument(distance(new Point(0, 0), getTurtle().getDestination()));
		getTurtle().moveTo(new Point(0, 0));
		return distance;
	}
}
