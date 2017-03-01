package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class SetHeadingCommand extends TurtleCommand {

	public SetHeadingCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		double degrees = parameters[0] - myTurtle.getRotation();
		view.turn(degrees);
		return degrees;
	}
}
