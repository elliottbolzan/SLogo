package model.commands.turtle;

import controller.Controller;
import parser.Argument;
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
	public Argument getReturnValue() {
		return 1;
	}

	@Override
	public Argument execute() {
		this.getController().setTurtleVisible(true);
	}
}
