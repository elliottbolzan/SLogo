package model.commands.math;

import model.parser.Argument;

public class SineCommand extends MathCommand {
	
	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument execute() {
		return new Argument(Math.sin((Math.toRadians(getParameter(0).getDouble()))));
	}
}
