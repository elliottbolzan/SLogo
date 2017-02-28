package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class CosCommand extends MathCommand {
	
	public CosCommand(int numParameters, String name) {
		super(1, name);
	}

	@Override
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view) {
		return Math.cos(parameters[0]);
	}
}
