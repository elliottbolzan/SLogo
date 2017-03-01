package model;

import java.util.ArrayList;

public abstract class ControlCommand {
    /**
     * The node's immediate children for use in calculating the syntax tree.
     */
    protected ArrayList<ControlCommand> myChildren;

    /**
     * Assigns a list to myChildren and empties a given one if necessary
     * 
     * @param childList An instance of a List of type AbstractNode
     */
    protected ControlCommand (ArrayList<ControlCommand> childList) {
        if (!childList.isEmpty()) {
            childList.clear();
        }

        myChildren = childList;
    }

    /**
     * Returns this node's value by running its command and parsing its children.
     * 
     * @return The node's integer value.
     * @throws ParseException
     */
    public int runAndGetValue(){
        return getValue();
    }

    /**
     * Gets the value of the encapsulated command
     * 
     * @return The return value of the command
     * @throws IllegalArgumentException
     * @throws ParseException
     */
    protected abstract int getValue ();

    /**
     * Returns the number of arguments that this node's command takes.
     * 
     * @return This node's required number of arguments.
     */
    public abstract int getNumOfArguments ();

    /**
     * Adds a child node.
     * 
     * @param node The node to be added.
     */
    public void addChildNode (ControlCommand node) {
        myChildren.add(node);
    }
}