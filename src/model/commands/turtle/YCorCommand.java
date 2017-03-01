package model.commands.turtle;

import controller.Controller;
import view.visualization.Turtle;

public class YCorCommand extends TurtleCommand {

	public YCorCommand() {
		super();
	}
	
	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		return myTurtle.getLocation().getY();
	}
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public double getReturnValue() {
		return this.getController().getTurtle().getLocation().getY();
	}

	@Override
	public void execute() {
		//Do nothing to the display
	}
}
