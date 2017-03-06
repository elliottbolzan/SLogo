package model.commands.math;

import model.commands.Command;
import model.parser.Argument;

public class AtanCommand extends Command {
	
	public AtanCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		return new Argument(Math.atan(getParameter(0).getDouble()));
	}
}
