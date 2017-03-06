package model.commands.turtle;

import controller.Controller;
import parser.Argument;
import view.visualization.Turtle;

public class YCorCommand extends TurtleCommand {

	public YCorCommand() {
		super();
	}
	
	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		return myTurtle.getDestination().getY();
	}
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument getReturnValue() {
		return this.getController().getTurtle().getDestination().getY();
	}

	@Override
	public Argument execute() {
		//Do nothing to the display
	}
}
