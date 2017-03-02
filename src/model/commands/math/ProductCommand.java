package model.commands.math;

import controller.Controller;
import view.visualization.Turtle;

public class ProductCommand extends MathCommand {
	
	public ProductCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public double getReturnValue() {
		return this.getParameterList().get(0) * this.getParameterList().get(1);
	}
}
