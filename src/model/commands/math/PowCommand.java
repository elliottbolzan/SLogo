package model.commands.math;

import controller.Controller;
import view.visualization.Turtle;

public class PowCommand extends MathCommand {
	
	public PowCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public double getReturnValue() {
		return Math.pow(this.getParameterList().get(0), this.getParameterList().get(1));
	}
}
