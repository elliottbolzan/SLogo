package model.commands.math;

import model.commands.Command;
import model.parser.Argument;

public class TanCommand extends Command {
	
	public TanCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		return new Argument(Math.tan(getParameter(0).getDouble()));
	}
}
