package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class SumCommand extends MathCommand {
	
	public SumCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return parameters[0] + parameters[1];
	}
}
