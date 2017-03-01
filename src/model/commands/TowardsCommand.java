package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class TowardsCommand extends TurtleCommand {

	public TowardsCommand(int numParameters, String name) {
		super(2, name);
	}
	
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		double xdiff = parameters[0] - myTurtle.getLocation().getX();
		double ydiff = parameters[1] - myTurtle.getLocation().getY();
		double tempd = Math.atan2(ydiff, xdiff);
		
		double degrees = Math.toDegrees(tempd) - myTurtle.getRotation();
		view.turn(degrees);
		return degrees;
	}
}
