package model.commands;

import controller.Controller;
import view.Turtle;

public abstract class MathCommand extends Command {

	protected MathCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	protected abstract double calcValue(double[] parameters, Turtle myTurtle, Controller view);
}
