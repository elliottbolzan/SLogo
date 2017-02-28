package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class MakeCommand extends Command {
	
	public MakeCommand(int numParameters, String name) {
		super(2, name);
	}

	@Override
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view) {
		return Math.sin(parameters[0]);
	}
}
