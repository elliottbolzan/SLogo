package model.commands;

import controller.Controller;
import view.Turtle;

public class SumCommand extends MathCommand {
	
	public SumCommand(int numParameters, String name) {
		super(1, name);
	}

	@Override
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view) {
		return parameters[0] + parameters[1];
	}
}
