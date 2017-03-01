package model;

import java.util.ArrayList;

public abstract class LoopCommand extends ControlCommand{
    protected StateStorage s;
    protected String varName;
    protected int start;
    protected int increment;
    protected int end;
    
    public LoopCommand (StateStorage store, String var) {
    	super(new ArrayList<ControlCommand>());
        s = store;
        varName = var;
    }
    
    @Override
    protected int getValue(){
        setVariables();
        return runLoops();
    }
    
    private int runLoops(){
        int lastValue = 0;
        for(int k=start ; k<=end ; k+=increment) {
            s.setVariable(new Variable(varName, k));
            lastValue = myChildren.get(myChildren.size() - 1).getValue();
        }
        
        return lastValue;
    }
    
    protected abstract void setVariables();
}