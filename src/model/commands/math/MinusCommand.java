package model.commands.math;

import model.parser.Argument;

public class MinusCommand extends MathCommand {
	
	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument execute() {
		return new Argument(-getParameter(0).getDouble());
	}
}
