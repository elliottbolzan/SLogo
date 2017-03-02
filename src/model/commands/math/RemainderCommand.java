package model.commands.math;

import controller.Controller;
import view.visualization.Turtle;

public class RemainderCommand extends MathCommand {
	
	public RemainderCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public double getReturnValue() {
		return (this.getParameterList().get(0) % this.getParameterList().get(1));
	}
}
