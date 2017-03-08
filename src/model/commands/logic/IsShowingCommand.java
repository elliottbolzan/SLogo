package model.commands.logic;

import model.parser.Argument;

public class IsShowingCommand extends LogicCommand {

	public IsShowingCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument execute() {
		return new Argument(super.booleanToInt(this.getController().getTurtleManager().getCurrentTurtle().isVisible()));
	}
}
