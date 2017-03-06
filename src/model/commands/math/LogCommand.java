package model.commands.math;

import controller.Controller;
import parser.Argument;
import view.visualization.Turtle;

public class LogCommand extends MathCommand {
	
	public LogCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument getReturnValue() {
		return Math.log(this.getParameterList().get(0));
	}
}
