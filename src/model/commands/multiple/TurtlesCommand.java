package model.commands.multiple;
import model.commands.Command;
import model.parser.Argument;

public class TurtlesCommand extends Command {
	@Override
	protected int internalNumParameters() {
		return 0;
	}
	@Override
	protected Argument execute() {
		return new Argument(getController().getTurtleManager().getAllTurtles().size());
	}
}