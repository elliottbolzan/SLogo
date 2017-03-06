package model.commands.turtle;

import controller.Controller;
import parser.Argument;
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
	public Argument getReturnValue() {
		return 0;
	}

	@Override
	public Argument execute() {
		this.getController().setTurtleVisible(false);
	}
}
