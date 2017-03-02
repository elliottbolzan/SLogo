package model.commands.control;

import java.util.ArrayList;

import model.commands.Command;

public abstract class ControlCommand extends Command {

	protected ArrayList<ControlCommand> cmdTree;

	protected ControlCommand(ArrayList<ControlCommand> previousTree) {
		if (!previousTree.isEmpty()) {
			previousTree.clear();
		}
		cmdTree = previousTree;
	}

	public abstract int numParameters();

	public void addNext(ControlCommand next) {
		cmdTree.add(next);
	}

    public abstract int numParameters();
    
    public void addNext(ControlCommand next) {
        cmdTree.add(next);
    }

}