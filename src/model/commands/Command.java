package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public abstract class Command implements CommandInterface {

	public Command() {
		
	}
	
	//TODO public abstract double execute();
	//TODO public abstract Command makeCommand(double[] parameters, Turtle myTurtle, Controller controller);

	public abstract double execute(double[] parameters, Turtle myTurtle, Controller controller);
}
