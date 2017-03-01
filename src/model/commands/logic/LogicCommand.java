package model.commands.logic;

import model.commands.Command;

public abstract class LogicCommand extends Command {

	protected LogicCommand(int numParameters, String name) {
		super();
	}

	protected int booleanToInt(boolean input) {
		return input ? 1 : 0;
	}
	
	@Override
	public void execute() {
		//Do nothing to the display
	}
}
