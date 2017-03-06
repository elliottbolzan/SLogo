package model.commands.math;

import controller.Controller;
import parser.Argument;
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
	public Argument getReturnValue() {
		return Math.atan(this.getParameterList().get(0));
	}
}
