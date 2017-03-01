package model.commands.math;

import controller.Controller;
import model.commands.Command;
import view.visualization.Turtle;

public abstract class MathCommand extends Command {

	protected MathCommand(int numParameters, String name) {
		super();
	}
	
	public abstract double execute(double[] parameters, Turtle myTurtle, Controller view);
}
