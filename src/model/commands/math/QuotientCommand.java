package model.commands.math;

import controller.Controller;
import view.visualization.Turtle;

public class QuotientCommand extends MathCommand {
	
	public QuotientCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public double getReturnValue() {
		return (this.getParameterList().get(0) / this.getParameterList().get(1));
	}
}
