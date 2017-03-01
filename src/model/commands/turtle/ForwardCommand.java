package model.commands.turtle;

import java.util.List;

import controller.Controller;
import utils.Point;
import view.visualization.Turtle;

public class ForwardCommand extends TurtleCommand {
	
	public ForwardCommand() {
		super();
	}
	
	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		Point loc = super.endLocation(parameters[0], myTurtle);
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
		Point loc = super.endLocation(this.getParameterList().get(0), this.getController().getTurtle());
		this.getController().moveTo(loc);
	}
}
