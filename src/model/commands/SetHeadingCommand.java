package model.commands;

import controller.Controller;
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
	public double getReturnValue() {
		return this.getParameterList().get(0) - this.getController().getTurtle().getRotation();
	}

	@Override
	public void execute() {
		double degrees = this.getParameterList().get(0) - this.getController().getTurtle().getRotation();
		this.getController().turn(degrees);
	}
}
