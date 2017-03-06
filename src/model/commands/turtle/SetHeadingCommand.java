package model.commands.turtle;

import controller.Controller;
import parser.Argument;
import view.visualization.Turtle;

public class SetHeadingCommand extends TurtleCommand {

	public SetHeadingCommand() {
		super();
	}
	
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		double degrees = parameters[0] - myTurtle.getRotation();
		view.turn(degrees);
		return degrees;
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument getReturnValue() {
		return this.getParameterList().get(0) - this.getController().getTurtle().getRotation();
	}

	@Override
	public Argument execute() {
		double degrees = this.getParameterList().get(0) - this.getController().getTurtle().getRotation();
		this.getController().turn(degrees);
	}
}
