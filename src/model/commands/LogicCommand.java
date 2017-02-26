package model.commands;

import controller.Controller;
import view.Turtle;

public abstract class LogicCommand extends Command {

	protected LogicCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	protected abstract int calcValue(int[] parameters, Turtle myTurtle, Controller view);
	
	protected int checker(boolean input){
		if(input) return 1;
		else return 0;
	}
}
