package model.commands.math;

import controller.Controller;
import view.visualization.Turtle;

public class SinCommand extends MathCommand {
	
	public SinCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public double getReturnValue() {
		return Math.sin(this.getParameterList().get(0));
	}
}
