package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public abstract class Command implements CommandInterface {
	private int myParameters;
	private String cmdName;

	public Command(int numOfParameters, String name) {
		myParameters = numOfParameters;
		cmdName = name;
	}
	
	public int numParameters(){
		return myParameters;
	}

	public double execute(int[] parameters, Turtle myTurtle, Controller view){
		return calcValue(parameters, myTurtle, view);
	}

	protected abstract double calcValue(int[] parameters, Turtle myTurtle, Controller view);

}
