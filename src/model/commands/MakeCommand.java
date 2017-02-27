package model.commands;

import controller.Controller;
import view.Turtle;

public class MakeCommand extends Command {
	
	public MakeCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view) {
		return Math.sin(parameters[0]);
	}
}
