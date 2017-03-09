package model.commands.turtle;

import model.parser.Argument;

public class ShowTurtleCommand extends RepeatableTurtleCommand {

	@Override
	protected int internalNumParameters() {
		return 0;
	}

	@Override
	protected Argument innerExecute() {
		getTurtle().setVisible(true);
		return new Argument(1);
	}
}
