package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class ProductCommand extends MathCommand {
	
	public ProductCommand(int numParameters, String name) {
		super(2, name);
	}

	@Override
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view) {
		return parameters[0] * parameters[1];
	}
}
