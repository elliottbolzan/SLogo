package model.commands;

import controller.Controller;
import view.Turtle;

public class PenQueryCommand extends LogicCommand {

	public PenQueryCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view){
		//return super.checker(myTurtle.getPen());
		return 0;
	}
}
