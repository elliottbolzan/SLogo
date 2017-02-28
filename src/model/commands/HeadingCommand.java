package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class HeadingCommand extends TurtleCommand {

	public HeadingCommand(int numParameters, String name) {
		super(0, name);
	}

	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		return myTurtle.getRotation();
	}
}
