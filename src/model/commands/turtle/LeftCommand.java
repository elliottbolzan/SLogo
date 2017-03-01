package model.commands.turtle;

import controller.Controller;
import view.visualization.Turtle;

public class LeftCommand extends TurtleCommand {

	public LeftCommand() {
		super();
	}
	
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		view.turn(parameters[0]);
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
		this.getController().turn(this.getParameterList().get(0));
	}
}
