package model.commands.math;

import controller.Controller;
import parser.Argument;
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
	public Argument getReturnValue() {
		return Math.tan(this.getParameterList().get(0));
	}
}
