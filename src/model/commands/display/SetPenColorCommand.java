package model.commands.display;

import model.commands.turtle.TurtleCommand;
import model.parser.Argument;

public class SetPenColorCommand extends TurtleCommand{
	public SetPenColorCommand(){
		super();
	}

	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		return null;
	}
	
}