package model.commands.turtle;

import controller.Controller;
import view.visualization.Turtle;

public class ShowTurtleCommand extends TurtleCommand {

	public ShowTurtleCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		view.setTurtleVisible(true);
		return 1;
	}
	
	@Override
	public int numParameters() {
		return 0;
	}
}
