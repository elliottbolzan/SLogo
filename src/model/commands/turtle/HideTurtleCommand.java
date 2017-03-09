package model.commands.turtle;

import model.parser.Argument;

public class HideTurtleCommand extends RepeatableTurtleCommand {
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument innerExecute() {
		getTurtle().setVisible(false);
		return new Argument(0);
	}
}
