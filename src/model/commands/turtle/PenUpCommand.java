package model.commands.turtle;

import controller.Controller;
import view.visualization.Turtle;

public class PenUpCommand extends TurtleCommand {

	public PenUpCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		view.setPenDown(false);
		return 0;
	}
	
	@Override
	public int numParameters() {
		return 0;
	}
}
