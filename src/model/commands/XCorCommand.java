package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class XCorCommand extends TurtleCommand {

	public XCorCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		return myTurtle.getLocation().getX();
	}
}
