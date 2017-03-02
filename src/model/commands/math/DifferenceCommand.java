package model.commands.math;

import controller.Controller;
import view.visualization.Turtle;

public class DifferenceCommand extends MathCommand {
	
	public DifferenceCommand() {
		super();
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return parameters[0] - parameters[1];
	}
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public double getReturnValue() {
		return this.getParameterList().get(0) - this.getParameterList().get(1);
	}
}
