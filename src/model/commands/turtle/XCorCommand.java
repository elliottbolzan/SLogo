package model.commands.turtle;

import controller.Controller;
import parser.Argument;
import view.visualization.Turtle;

public class XCorCommand extends TurtleCommand {

	public XCorCommand() {
		super();
	}
	
	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		return myTurtle.getLocation().getX();
	}
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument getReturnValue() {
		return this.getController().getTurtle().getLocation().getX();
	}

	@Override
	public Argument execute() {
		//Do nothing to the display
	}
}
