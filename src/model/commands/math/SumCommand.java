package model.commands.math;

import model.parser.Argument;

public class SumCommand extends MathCommand {
	
	@Override
	protected int internalNumParameters() {
		return 2;
	}

	@Override
	protected Argument execute() {
		return new Argument(getParameter(0).getDouble() + getParameter(1).getDouble());
	}
	
}
