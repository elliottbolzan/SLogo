package model.commands;

import controller.Controller;
import view.Turtle;

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

	public void execute(int[] parameters, Turtle myTurtle, Controller view){
		calcValue(parameters, myTurtle, view);
	}

	protected abstract int calcValue(int[] parameters, Turtle myTurtle, Controller view);

}
