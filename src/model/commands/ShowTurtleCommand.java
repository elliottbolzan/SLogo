package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class ShowTurtleCommand extends TurtleCommand {

	public ShowTurtleCommand() {
		super();
	}

	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		view.setTurtleVisible(true);
		return 1;
	}
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public double getReturnValue() {
		return 1;
	}

	@Override
	public void execute() {
		this.getController().setTurtleVisible(true);
	}
}
