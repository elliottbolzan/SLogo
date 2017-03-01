package model.commands.math;

import controller.Controller;
import view.visualization.Turtle;

public class LogCommand extends MathCommand {
	
	public LogCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return Math.log(parameters[0]);
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public double getReturnValue() {
		return Math.log(this.getParameterList().get(0));
	}
}
