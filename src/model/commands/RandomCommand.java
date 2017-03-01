package model.commands;

import java.util.Random;
import controller.Controller;
import view.visualization.Turtle;

public class RandomCommand extends MathCommand {
	Random value;
	
	public RandomCommand(int numParameters, String name) {
		super(numParameters, name);
		value = new Random();
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return value.nextInt((int) parameters[0]);
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
