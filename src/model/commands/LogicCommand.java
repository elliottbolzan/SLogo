package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public abstract class LogicCommand extends Command {

	protected LogicCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	protected abstract double calcValue(double[] parameters, Turtle myTurtle, Controller view);
	
	protected int checker(boolean input){
		if(input) return 1;
		else return 0;
	}
}
