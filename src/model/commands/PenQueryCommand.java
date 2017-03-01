package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class PenQueryCommand extends LogicCommand {

	public PenQueryCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		return super.booleanToInt(myTurtle.isPenDown());
	}
}
