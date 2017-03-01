package model;

import java.util.ArrayList;

public abstract class ControlCommand {

    protected ArrayList<ControlCommand> myChildren;

    protected ControlCommand (ArrayList<ControlCommand> childList) {
        if (!childList.isEmpty()) {
            childList.clear();
        }
        myChildren = childList;
    }

    protected abstract double execute(String cmd, String[] varList, String[] cmdList, double expr, StateStorage s);
    public abstract int getNumOfArguments();
    
    public void addChildNode (ControlCommand node) {
        myChildren.add(node);
    }
}