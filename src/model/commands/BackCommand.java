package model.commands;

import controller.Controller;
import utils.Point;
import view.visualization.Turtle;

public class BackCommand extends TurtleCommand {

	public BackCommand() {
		super();
	}
	
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		Point loc = super.endLocation(-1 * parameters[0], myTurtle);
		view.moveTo(loc);
		return parameters[0];
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public double getReturnValue() {
		return this.getParameterList().get(0);
	}

	@Override
	public void execute() {
		Point loc = super.endLocation(-1 * this.getParameterList().get(0), this.getController().getTurtle());
		this.getController().moveTo(loc);
	}
}
