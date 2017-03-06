package model.commands.math;

import controller.Controller;
import parser.Argument;
import view.visualization.Turtle;

public class CosCommand extends MathCommand {
	
	public CosCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument getReturnValue() {
		return Math.cos(this.getParameterList().get(0));
	}
}
