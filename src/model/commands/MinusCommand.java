package model.commands;

import controller.Controller;
import view.Turtle;

public class MinusCommand extends MathCommand {
	
	public MinusCommand(int numParameters, String name) {
		super(1, name);
	}

	@Override
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view) {
		return parameters[0] * -1;
	}
}
