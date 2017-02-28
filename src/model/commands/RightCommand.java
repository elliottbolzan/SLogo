package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class RightCommand extends TurtleCommand {

	public RightCommand(int numParameters, String name) {
		super(1, name);
	}

	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		view.turn(-parameters[0]);
		return parameters[0];
	}
}
