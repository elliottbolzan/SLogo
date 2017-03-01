package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class RightCommand extends TurtleCommand {

	public RightCommand() {
		super();
	}

	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		view.turn(-parameters[0]);
		return parameters[0];
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public double getReturnValue() {
		return this.getParameterList().get(0);
	}

	@Override
	public void execute() {
		this.getController().turn(-this.getParameterList().get(0));
	}
}
