package model.commands.math;

import model.parser.Argument;

public class PiCommand extends MathCommand {
	
	@Override
	protected int internalNumParameters() {
		return 0;
	}

	@Override
	protected Argument execute() {
		return new Argument(Math.PI);
	}
}
