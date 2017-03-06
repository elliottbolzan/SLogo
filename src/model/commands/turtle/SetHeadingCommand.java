package model.commands.turtle;

import model.parser.Argument;

public class SetHeadingCommand extends TurtleCommand {

	public SetHeadingCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}


	@Override
	public Argument execute() {
		double degrees = getParameter(0).getDouble() - this.getController().getTurtle().getRotation();
		this.getController().turn(degrees);
		return new Argument(degrees);
	}
}
