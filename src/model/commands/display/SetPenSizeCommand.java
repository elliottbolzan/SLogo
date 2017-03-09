package model.commands.display;

import model.commands.turtle.TurtleCommand;
import model.parser.Argument;

public class SetPenSizeCommand extends TurtleCommand {

	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument execute() {
		int width = (int) this.getParameter(0).getDouble();
		getController().getTurtleManager().setTurtlePenSize(width);
		return new Argument(width);
	}

}