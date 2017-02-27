package model.commands;

import controller.Controller;
import view.Turtle;

public class HeadingCommand extends TurtleCommand {

	public HeadingCommand(int numParameters, String name) {
		super(0, name);
	}
	
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view){
		return myTurtle.getRotation();
	}
}
