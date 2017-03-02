package model.commands.math;

import controller.Controller;
import view.visualization.Turtle;

public class TanCommand extends MathCommand {
	
	public TanCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public double getReturnValue() {
		return Math.tan(this.getParameterList().get(0));
	}
}
