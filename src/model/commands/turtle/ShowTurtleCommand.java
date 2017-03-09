package model.commands.turtle;

import model.parser.Argument;

public class ShowTurtleCommand extends RepeatableTurtleCommand {

	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument innerExecute() {
		getTurtle().setVisible(true);
		return new Argument(1);
	}
}
