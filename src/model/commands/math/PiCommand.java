package model.commands.math;

import model.commands.Command;
import model.parser.Argument;

public class PiCommand extends Command {
	
	public PiCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument execute() {
		return new Argument(Math.PI);
	}
}
