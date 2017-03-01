package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class HeadingCommand extends TurtleCommand {

	public HeadingCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		return myTurtle.getRotation();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}
}
