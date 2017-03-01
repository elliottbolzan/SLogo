package model.commands.math;

import controller.Controller;
import view.visualization.Turtle;

public class CosCommand extends MathCommand {
	
	public CosCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return Math.cos(parameters[0]);
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public double getReturnValue() {
		return Math.cos(this.getParameterList().get(0));
	}
}
