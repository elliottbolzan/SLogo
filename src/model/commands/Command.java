package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public abstract class Command /*implements CommandInterface*/ {

	private int myParameters;
	private String cmdName;

	public Command(int numOfParameters, String name) {
		myParameters = numOfParameters;
		cmdName = name;
	}

	public int numParameters() {
		return myParameters;
	}

	public abstract double execute(double[] parameters, Turtle myTurtle, Controller controller);
}
