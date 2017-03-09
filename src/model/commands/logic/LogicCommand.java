package model.commands.logic;

import model.commands.Command;

public abstract class LogicCommand extends Command {

	protected int booleanToInt(boolean input) {
		return input ? 1 : 0;
	}
	
}
