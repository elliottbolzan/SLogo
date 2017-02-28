package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class SinCommand extends MathCommand {
	
	public SinCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(int[] parameters, Turtle myTurtle, Controller view) {
		return Math.sin(parameters[0]);
	}
}
