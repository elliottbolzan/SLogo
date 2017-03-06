package model.commands.logic;

import controller.Controller;
import parser.Argument;
import view.visualization.Turtle;

public class NotCommand extends LogicCommand {

	public NotCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument getReturnValue() {
		return super.booleanToInt(this.getParameterList().get(0) == 0);
	}
}
