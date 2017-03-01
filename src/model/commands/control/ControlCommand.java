package model.commands.control;

import java.util.ArrayList;
import model.StateStorage;
import model.commands.Command;

public abstract class ControlCommand{

    protected ArrayList<ControlCommand> cmdTree;

    protected ControlCommand (ArrayList<ControlCommand> previousTree) {
        if (!previousTree.isEmpty()) {
            previousTree.clear();
        }
        cmdTree = previousTree;
    }

    protected abstract double execute();
    public abstract int numParameters();
    
    public void addNext(ControlCommand next) {
        cmdTree.add(next);
    }
}