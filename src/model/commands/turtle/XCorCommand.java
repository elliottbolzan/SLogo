package model.commands.turtle;

import controller.Controller;
import view.visualization.Turtle;

public class XCorCommand extends TurtleCommand {

	public XCorCommand() {
		super();
	}
	
	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		return myTurtle.getLocation().getX();
	}
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public double getReturnValue() {
		return this.getController().getTurtle().getLocation().getX();
	}

	@Override
	public void execute() {
		//Do nothing to the display
	}
}
