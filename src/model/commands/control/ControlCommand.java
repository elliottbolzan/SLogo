package model.commands.control;

import java.util.ArrayList;

import model.commands.Command;

<<<<<<< HEAD
public abstract class ControlCommand extends Command{
=======
public abstract class ControlCommand extends Command {

	protected ArrayList<ControlCommand> cmdTree;

	protected ControlCommand(ArrayList<ControlCommand> previousTree) {
		if (!previousTree.isEmpty()) {
			previousTree.clear();
		}
		cmdTree = previousTree;
	}
>>>>>>> master

	public abstract int numParameters();

	public void addNext(ControlCommand next) {
		cmdTree.add(next);
	}

<<<<<<< HEAD
    public abstract int numParameters();
    
    public void addNext(ControlCommand next) {
        cmdTree.add(next);
    }
=======
>>>>>>> master
}