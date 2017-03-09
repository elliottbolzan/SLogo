package model.commands.math;

import model.commands.Command;
import model.parser.Argument;

public class AtanCommand extends Command {
	
	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument execute() {
		return new Argument(Math.atan(getParameter(0).getDouble()));
	}
}
