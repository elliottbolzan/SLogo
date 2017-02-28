package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class PenDownCommand extends TurtleCommand {

	public PenDownCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	public double execute(int[] parameters, Turtle myTurtle, Controller view){
		view.setPenDown(true);
		return 1;
	}
}
