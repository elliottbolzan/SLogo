package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class ShowTurtleCommand extends TurtleCommand {

	public ShowTurtleCommand(int numParameters, String name) {
		super(0, name);
	}
	
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view){
		view.setTurtleVisible(true);
		return 1;
	}
}
