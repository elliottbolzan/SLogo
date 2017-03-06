package model.commands.math;

import model.commands.Command;
import model.parser.Argument;

public class CosCommand extends Command {
	
	public CosCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		return new Argument(Math.cos(this.getParameter(0).getDouble()));
	}
}
