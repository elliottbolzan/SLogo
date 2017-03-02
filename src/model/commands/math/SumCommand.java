package model.commands.math;

import controller.Controller;
import view.visualization.Turtle;

public class SumCommand extends MathCommand {
	
	public SumCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public double getReturnValue() {
		return (this.getParameterList().get(0) + this.getParameterList().get(1));
	}
}
