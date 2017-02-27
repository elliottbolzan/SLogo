package model.commands;

import controller.Controller;
import view.Turtle;

public class YCorCommand extends TurtleCommand {

	public YCorCommand(int numParameters, String name) {
		super(0, name);
	}
	
	@Override
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view){
		return myTurtle.getLocation().getY();
	}
}
