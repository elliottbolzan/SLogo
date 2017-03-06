package model.commands.turtle;

import controller.Controller;
import model.parser.Argument;
import view.visualization.Turtle;

public class HeadingCommand extends TurtleCommand {

	public HeadingCommand() {
		super();
	}

	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		return myTurtle.getRotation();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		return new Argument(this.getController().getTurtle().getRotation());
	}
}
