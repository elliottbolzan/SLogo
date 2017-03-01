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
    protected double execute(String cmd, String[] varList, String[] cmdList, double expr, StateStorage s){
        setVariables();
        return runLoops(cmd, varList, cmdList, expr, s);
    }
    
    private double runLoops(String cmd, String[] varList, String[] cmdList, double expr, StateStorage s){
        int lastValue = 0;
        for(int k=start ; k<=end ; k+=increment) {
            s.setVariable(new Variable(varName, k));
            lastValue = (int) myChildren.get(myChildren.size() - 1).execute(cmd, varList, cmdList, expr, s);
        }
        
        return lastValue;
    }
    
    protected abstract void setVariables();
}