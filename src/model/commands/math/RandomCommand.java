package model.commands.math;

import java.util.Random;
import controller.Controller;
import view.visualization.Turtle;

public class RandomCommand extends MathCommand {
	Random value;
	
	public RandomCommand() {
		super();
		value = new Random();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public double getReturnValue() {
		return value.nextInt(this.getParameterList().get(0).intValue());
	}
}
