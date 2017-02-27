package model.commands;

import controller.Controller;
import view.Turtle;

public class PenDownCommand extends TurtleCommand {

	public PenDownCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view){
		view.setPenDown(true);
		return 1;
	}
}
