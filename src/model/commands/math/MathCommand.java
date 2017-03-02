package model.commands.math;

import model.commands.Command;

public abstract class MathCommand extends Command {

	protected MathCommand() {
		super();
	}
	
	@Override
	public void execute() {
		//Do nothing to the display
	}
}
