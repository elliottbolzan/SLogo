package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class HeadingCommand extends TurtleCommand {

	public HeadingCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view){
		return myTurtle.getRotation();
	}
}
