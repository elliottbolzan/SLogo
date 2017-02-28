package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class HideTurtleCommand extends TurtleCommand {

	public HideTurtleCommand(int numParameters, String name) {
		super(0, name);
	}
	
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view){
		view.setTurtleVisible(false);
		return 0;
	}
}
