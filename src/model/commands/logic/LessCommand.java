package model.commands.logic;

import controller.Controller;
import parser.Argument;
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
	public Argument getReturnValue() {
		return super.booleanToInt(this.getParameterList().get(0) < this.getParameterList().get(1));
	}
}
