package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class ShowQueryCommand extends LogicCommand {

	public ShowQueryCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	//where do i get show boolean?
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		//return super.checker(myTurtle.getPen());
		return 0;
	}
}
