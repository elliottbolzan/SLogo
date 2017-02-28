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
	public double execute(int[] parameters, Turtle myTurtle, Controller view) {
		return value.nextInt(parameters[0]);
	}
}
