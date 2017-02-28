package model.commands;

import controller.Controller;
import utils.Point;
import view.visualization.Turtle;

public class HomeCommand extends TurtleCommand {

	public HomeCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view){
		view.moveTo(new Point(parameters[0],parameters[1]));
		return super.distance(parameters,myTurtle);
	}
}
