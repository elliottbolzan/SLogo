package model.commands.math;

import controller.Controller;
import view.visualization.Turtle;

public class AtanCommand extends MathCommand {
	
	public AtanCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public double getReturnValue() {
		return Math.atan(this.getParameterList().get(0));
	}
}
