package model.commands.logic;

import parser.Argument;

public class AndCommand extends LogicCommand {

	public AndCommand() {
		super();
	}

	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument getReturnValue() {
		return super.booleanToInt(this.getParameterList().get(0) != 0 && this.getParameterList().get(1) != 0);
	}
}
