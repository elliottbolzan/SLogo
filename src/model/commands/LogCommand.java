package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class LogCommand extends MathCommand {
	
	public LogCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return Math.log(parameters[0]);
	}
	
	@Override
	public int numParameters() {
		return 1;
	}
}
