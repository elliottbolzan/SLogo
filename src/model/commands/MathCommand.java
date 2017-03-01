package model.commands;

public abstract class MathCommand extends Command {

	protected MathCommand(int numParameters, String name) {
		super();
	}
	
	@Override
	public void execute() {
		//Do nothing to the display
	}
}
