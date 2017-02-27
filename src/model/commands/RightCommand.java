package model.commands;

import controller.Controller;
import view.Turtle;

public class RightCommand extends TurtleCommand {

	public RightCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view){
		double degrees = myTurtle.getRotation() + parameters[0];
		view.turn(degrees);
		return parameters[0];
	}
}