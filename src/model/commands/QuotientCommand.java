package model.commands;

import controller.Controller;
import view.Turtle;

public class QuotientCommand extends MathCommand {
	
	public QuotientCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view) {
		return parameters[0] / parameters[1];
	}
}
