package model.commands;

import controller.Controller;
import utils.Point;
import view.visualization.Turtle;

public class SetXYCommand extends TurtleCommand {

	public SetXYCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		double distance = super.distance(parameters, myTurtle);
		Point loc = new Point(parameters[0], parameters[1]);
		view.moveTo(loc);
		return distance;
	}
}
