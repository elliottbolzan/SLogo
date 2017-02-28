package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class AtanCommand extends MathCommand {
	
	public AtanCommand(int numParameters, String name) {
		super(1, name);
	}

	@Override
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view) {
		return Math.atan(parameters[0]);
	}
}
