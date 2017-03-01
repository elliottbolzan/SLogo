package model.commands.turtle;

import controller.Controller;
import view.visualization.Turtle;

public class HeadingCommand extends TurtleCommand {

	public HeadingCommand() {
		super();
	}

	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		return myTurtle.getRotation();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public double getReturnValue() {
		return this.getController().getTurtle().getRotation();
	}

	@Override
	public void execute() {
		//Do nothing to the display
	}
}
