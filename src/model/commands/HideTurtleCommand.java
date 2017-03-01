package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class HideTurtleCommand extends TurtleCommand {

	public HideTurtleCommand() {
		super();
	}
	
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		view.setTurtleVisible(false);
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
		this.getController().setTurtleVisible(false);
	}
}
