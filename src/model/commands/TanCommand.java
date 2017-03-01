package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class TanCommand extends MathCommand {
	
	public TanCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return Math.tan(parameters[0]);
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public double getReturnValue() {
		return Math.tan(this.getParameterList().get(0));
	}
}
