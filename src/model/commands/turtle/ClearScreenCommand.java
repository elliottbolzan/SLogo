package model.commands.turtle;
import model.parser.Argument;
import utils.Point;


public class ClearScreenCommand extends RepeatableTurtleCommand {
	@Override
	protected int internalNumParameters() {
		return 0;
	}
	@Override
	protected Argument innerExecute() {
		double distance = distance(getTurtle().getDestination(), new Point(0, 0));
		getTurtle().moveTo(new Point(0, 0));
		getController().clearDisplay();
		return new Argument(distance);
	}
}