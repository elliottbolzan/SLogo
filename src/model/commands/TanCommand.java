package model.commands;

import controller.Controller;
import view.Turtle;

public class TanCommand extends MathCommand {
	
	public TanCommand(int numParameters, String name) {
		super(1, name);
	}

	@Override
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view) {
		return Math.tan(parameters[0]);
	}
}
