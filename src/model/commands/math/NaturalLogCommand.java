package model.commands.math;

import model.parser.Argument;

public class NaturalLogCommand extends MathCommand {
	
	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument execute() {
		return new Argument(Math.log(getParameter(0).getDouble()));
	}
}
