package model.commands;

import controller.Controller;
import utils.Point;
import view.visualization.Turtle;

public class HomeCommand extends TurtleCommand {

	public HomeCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	public double execute(int[] parameters, Turtle myTurtle, Controller view) {
		view.moveTo(new Point(0, 0));
		return super.distance(new int[]{0, 0}, myTurtle);
	}
}
