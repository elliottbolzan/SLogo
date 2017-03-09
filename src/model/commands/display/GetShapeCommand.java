package model.commands.display;

import model.commands.turtle.TurtleCommand;
import model.parser.Argument;

public class GetShapeCommand extends TurtleCommand{
	public GetShapeCommand(){
		super();
	}

	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument execute() {
		return new Argument(getTurtle().getShapeIndex());
	}
	
}