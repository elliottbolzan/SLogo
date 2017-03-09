package model.commands.turtle;

import model.parser.Argument;

public class HideTurtleCommand extends RepeatableTurtleCommand {
	
	@Override
	protected int internalNumParameters() {
		return 0;
	}

	@Override
	protected Argument innerExecute() {
		getTurtle().setVisible(false);
		return new Argument(0);
	}
}
