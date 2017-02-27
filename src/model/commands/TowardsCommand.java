package model.commands;

import controller.Controller;
import view.Turtle;

public class TowardsCommand extends TurtleCommand {

	public TowardsCommand(int numParameters, String name) {
		super(2, name);
	}
	
	//how to calc angle? where is zero?
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view){
		double xdiff = parameters[0] - myTurtle.getLocation().getX();
		double ydiff = parameters[1] - myTurtle.getLocation().getY();
		double tempd = Math.atan2(ydiff, xdiff);
		
		double degrees = parameters[0] - Math.toDegrees(tempd);
		view.turn(degrees);
		return degrees;
	}
}
