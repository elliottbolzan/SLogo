package model.commands;

import controller.Controller;
import view.Turtle;

public class ShowQueryCommand extends LogicCommand {

	public ShowQueryCommand(int numParameters, String name) {
		super(0, name);
	}
	
	//where do i get show boolean?
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view){
		//return super.checker(myTurtle.getPen());
		return 0;
	}
}
