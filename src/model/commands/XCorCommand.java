package model.commands;

import controller.Controller;
import view.Turtle;

public class XCorCommand extends TurtleCommand {

	public XCorCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	@Override
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view){
		return myTurtle.getLocation().getX();
	}
}
