package model.commands;

import controller.Controller;
import utils.Point;
import view.Turtle;

public class BackCommand extends TurtleCommand {

	public BackCommand(int numParameters, String name) {
		super(1, name);
	}
	
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view){
		Point loc = super.endLocation(-1 * parameters[0], myTurtle);
		view.moveTo(loc);
		return parameters[0];
	}
}
