package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class PenUpCommand extends TurtleCommand {

	public PenUpCommand(int numParameters, String name) {
		super(0, name);
	}
	
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view){
		view.setPenDown(false);
		return 0;
	}
}
