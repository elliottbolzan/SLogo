package model.commands.math;

import model.commands.Command;
import model.parser.Argument;

public class PowerCommand extends Command {
	
	public PowerCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument execute() {
		return new Argument(Math.pow(getParameter(0).getDouble(), getParameter(1).getDouble()));
	}
}
