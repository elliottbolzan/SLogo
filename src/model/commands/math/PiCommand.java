package model.commands.math;

import controller.Controller;
import view.visualization.Turtle;

public class PiCommand extends MathCommand {
	
	public PiCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public double getReturnValue() {
		return Math.PI;
	}
}