package model.commands.math;

import controller.Controller;
import view.visualization.Turtle;

public class MinusCommand extends MathCommand {
	
	public MinusCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return parameters[0] * -1;
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public double getReturnValue() {
		return this.getParameterList().get(0) * -1;
	}
}
