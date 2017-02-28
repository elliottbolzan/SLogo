package model.commands;

import controller.Controller;
import view.Turtle;

public class RightCommand extends TurtleCommand {

	public RightCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view) {
		view.turn(-parameters[0]);
		return parameters[0];
	}
}
