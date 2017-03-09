package model.commands.display;

import model.commands.turtle.TurtleCommand;
import model.parser.Argument;

public class GetPenColorCommand extends TurtleCommand{
	public GetPenColorCommand(){
		super();
	}

	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument execute() {	
		
		return new Argument(getTurtle().getPenColorIndex());
	}
}