package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class LogCommand extends MathCommand {
	
	public LogCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view) {
		return Math.log(parameters[0]);
	}
}
