package model.commands.control;

import java.util.ArrayList;

import model.StateStorage;
import model.Variable;

public abstract class LoopCommand extends ControlCommand{
    protected StateStorage s;
    protected String varName;
    protected int start;
    protected int increment;
    protected int end;
    protected int myValue;
    
    public LoopCommand (StateStorage store, String var) {
    	super(new ArrayList<ControlCommand>());
        s = store;
        varName = var;
    }
    
    @Override
	public void execute(){
        setVar();
        loop();
    }
    
    private void loop(){
        double previous = 0;
        for(int k=start ; k<=end ; k+=increment) {
            s.setVariable(new Variable(varName, k));
            previous = cmdTree.get(cmdTree.size() - 1).execute();
        }
        previous = myValue;
    }
    
    public double getReturnValue(){
    	return myValue;
    }
    
    protected abstract void setVar();
}