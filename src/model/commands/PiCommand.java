package model.commands;

import controller.Controller;
import view.Turtle;

public class PiCommand extends MathCommand {
	
	public PiCommand(int numParameters, String name) {
		super(0, name);
	}

	@Override
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view) {
		return Math.PI;
	}
}
