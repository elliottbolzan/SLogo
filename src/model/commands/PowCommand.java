package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class PowCommand extends MathCommand {
	
	public PowCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return Math.pow(parameters[0], parameters[1]);
	}
}
