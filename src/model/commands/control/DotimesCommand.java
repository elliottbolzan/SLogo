package model.commands.control;

import model.StateStorage;

public class DotimesCommand extends LoopCommand {
	public DotimesCommand(StateStorage store, String var) {
		super(store, var);
	}

	@Override
	protected void setVar() {
		start = 0;
		//end = (int) cmdTree.get(0).execute();
		increment = 1;
	}

	@Override
	public int numParameters() {
		return 2;
	}
}
