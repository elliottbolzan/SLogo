package model.commands.math;

import model.commands.Command;
import model.parser.Argument;

public class SineCommand extends Command {
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		return new Argument(Math.sin((Math.toRadians(getParameter(0).getDouble()))));
	}
}
