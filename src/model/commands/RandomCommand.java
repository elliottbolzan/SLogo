package model.commands;

import java.util.Random;
import controller.Controller;
import view.visualization.Turtle;

public class RandomCommand extends MathCommand {
	Random value;
	
	public RandomCommand(int numParameters, String name) {
		super(1, name);
		value = new Random();
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return value.nextInt((int) parameters[0]);
	}
}
