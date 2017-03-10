package model.commands.math;

import model.parser.Argument;

public class CosineCommand extends MathCommand {

	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument execute() {
		return new Argument(Math.cos(Math.toRadians(this.getParameter(0).getDouble())));
	}
}
