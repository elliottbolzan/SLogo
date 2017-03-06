package model.commands.math;

import model.commands.Command;
import model.parser.Argument;

public class LogCommand extends Command {
	
	public LogCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		return new Argument(Math.log(getParameter(0).getDouble()));
	}
}
