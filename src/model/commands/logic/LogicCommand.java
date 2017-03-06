package model.commands.logic;

import model.commands.Command;
import parser.Argument;

public abstract class LogicCommand extends Command {

	protected LogicCommand() {
		super();
	}

	protected int booleanToInt(boolean input) {
		return input ? 1 : 0;
	}
	
	@Override
	public Argument execute() {
		//Do nothing to the display
	}
}
