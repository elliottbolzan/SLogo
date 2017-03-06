package model.commands.logic;

import model.parser.Argument;

public class IsPenDownCommand extends LogicCommand {

	public IsPenDownCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument execute() {
		return new Argument(super.booleanToInt(this.getController().getTurtle().isPenDown()));
	}
}
