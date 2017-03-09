package model.commands.math;

import model.commands.Command;
import model.parser.Argument;

public class PowCommand extends Command {
	
	@Override
	protected int internalNumParameters() {
		return 2;
	}

	@Override
	protected Argument execute() {
		return new Argument(Math.pow(getParameter(0).getDouble(), getParameter(1).getDouble()));
	}
}
