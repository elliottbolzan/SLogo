package model.commands.turtle;

import model.parser.Argument;

public class HideTurtleCommand extends TurtleCommand {

	public HideTurtleCommand() {
		super();
	}

	
	@Override
	public int numParameters() {
		return 0;
	}



	@Override
	public Argument execute() {
		getController().getTurtleManager().setTurtleVisible(false);
		return new Argument(0);
	}
}
