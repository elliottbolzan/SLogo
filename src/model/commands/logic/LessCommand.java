package model.commands.logic;

import controller.Controller;
import view.visualization.Turtle;

public class LessCommand extends LogicCommand {

	public LessCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public double getReturnValue() {
		return super.booleanToInt(this.getParameterList().get(0) < this.getParameterList().get(1));
	}
}
