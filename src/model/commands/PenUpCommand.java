package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class PenUpCommand extends TurtleCommand {

	public PenUpCommand() {
		super();
	}
	
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		view.setPenDown(false);
		return 0;
	}
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public double getReturnValue() {
		return 0;
	}

	@Override
	public void execute() {
		this.getController().setPenDown(false);
	}
}
