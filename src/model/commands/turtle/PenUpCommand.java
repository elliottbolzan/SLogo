package model.commands.turtle;

import controller.Controller;
import parser.Argument;
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
	public Argument getReturnValue() {
		return 0;
	}

	@Override
	public Argument execute() {
		this.getController().setPenDown(false);
	}
}
