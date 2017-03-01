package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class AtanCommand extends MathCommand {
	
	public AtanCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return Math.atan(parameters[0]);
	}
}
