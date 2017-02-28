package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class PenDownCommand extends TurtleCommand {

	public PenDownCommand(int numParameters, String name) {
		super(0, name);
	}
	
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view){
		view.setPenDown(true);
		return 1;
	}
}
