package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public abstract class MathCommand extends Command {

	protected MathCommand(int numParameters, String name) {
		super();
	}
	
	public abstract double execute(double[] parameters, Turtle myTurtle, Controller view);
}
