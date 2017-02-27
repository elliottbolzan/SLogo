package model.commands;

import controller.Controller;
import utils.Point;
import view.Turtle;

public class ClearScreenCommand extends TurtleCommand {

	public ClearScreenCommand(int numParameters, String name) {
		super(0, name);
	}
	
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view){
		view.clearDisplay();
		view.moveTo(new Point(0,0));
		return super.distance(parameters,myTurtle);
	}
}
