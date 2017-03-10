package model.commands.math;

import model.parser.Argument;

public class TangentCommand extends MathCommand {

	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument execute() {
		return new Argument(Math.tan(getParameter(0).getDouble()));
	}
	
}
