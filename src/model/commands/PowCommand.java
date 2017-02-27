package model.commands;

import controller.Controller;
import view.Turtle;

public class PowCommand extends MathCommand {
	
	public PowCommand(int numParameters, String name) {
		super(1, name);
	}

	@Override
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view) {
		return Math.pow(parameters[0], parameters[1]);
	}
}
