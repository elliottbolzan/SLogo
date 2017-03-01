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

    public int runAndGetValue(){
        return getValue();
    }

    protected abstract int getValue ();
    public abstract int getNumOfArguments ();
    
    public void addChildNode (ControlCommand node) {
        myChildren.add(node);
    }
}