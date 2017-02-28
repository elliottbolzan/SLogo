package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class LeftCommand extends TurtleCommand {

	public LeftCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		view.turn(parameters[0]);
		return parameters[0];
	}
}
