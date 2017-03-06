package model.commands.turtle;

import model.parser.Argument;

public class ShowTurtleCommand extends TurtleCommand {

	public ShowTurtleCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument execute() {
		this.getController().setTurtleVisible(true);
		return new Argument(1);
	}
}
