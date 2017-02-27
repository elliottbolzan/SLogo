package model.commands;

import controller.Controller;
import view.Turtle;

public class PiCommand extends MathCommand {
	
	public PiCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view) {
		return Math.PI;
	}
}
