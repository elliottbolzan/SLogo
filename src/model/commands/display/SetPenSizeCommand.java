package model.commands.display;

import model.commands.turtle.RepeatableTurtleCommand;
import model.parser.Argument;

public class SetPenSizeCommand extends RepeatableTurtleCommand {

	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument innerExecute() {
		int width = (int) this.getParameter(0).getDouble();
		getTurtle().setPenWidth(width);
		return new Argument(width);
	}

}