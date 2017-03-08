package model.commands.turtle;

import model.parser.Argument;
import utils.Point;

public class HomeCommand extends TurtleCommand {

	public HomeCommand() {
		super();
	}

	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument execute() {
		Argument dist = new Argument(distance(new double[]{0, 0}, getController().getTurtleManager().getCurrentTurtle()));
		getController().getTurtleManager().moveTo(new Point(0, 0));
		return dist;
	}
}
