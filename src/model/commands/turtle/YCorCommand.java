package model.commands.turtle;

import controller.Controller;
import view.visualization.Turtle;

public class YCorCommand extends TurtleCommand {

	public YCorCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		return myTurtle.getLocation().getY();
	}
	
	@Override
	public int numParameters() {
		return 0;
	}
}
