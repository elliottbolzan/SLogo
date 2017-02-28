package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public abstract class MathCommand extends Command {

	protected MathCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	public abstract double execute(int[] parameters, Turtle myTurtle, Controller view);
}
