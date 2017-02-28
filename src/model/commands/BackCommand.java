package model.commands;

import controller.Controller;
import utils.Point;
import view.visualization.Turtle;

public class BackCommand extends TurtleCommand {

	public BackCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view){
		Point loc = super.endLocation(-1 * parameters[0], myTurtle);
		view.moveTo(loc);
		return parameters[0];
	}
}
