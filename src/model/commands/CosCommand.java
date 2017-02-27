package model.commands;

import controller.Controller;
import view.Turtle;

public class CosCommand extends MathCommand {
	
	public CosCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view) {
		return Math.cos(parameters[0]);
	}
}
