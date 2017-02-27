package model.commands;

import controller.Controller;
import view.Turtle;

public class AtanCommand extends MathCommand {
	
	public AtanCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	protected double calcValue(Double[] parameters, Turtle myTurtle, Controller view) {
		return Math.atan(parameters[0]);
	}
}
