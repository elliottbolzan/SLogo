
public abstract class LoopCommand {
    protected StateStorage s;
    protected String varName;
    protected int start;
    protected int increment;
    protected int end;
    
    /**
     * Instantiates a new ForNode
     */
    public LoopCommand (StateStorage store, String var) {
        s = store;
        varName = var;
    }
    
    @Override
    protected int getValue() throws IllegalArgumentException, ParseException {
        setVariables();
        return runLoops();
    }
    
    private int runLoops() throws IllegalArgumentException, ParseException {
        int lastValue = 0;
        for(int n = myStart ; n <= myEnd ; n += myIncrement) {
            myScope.setVariable(myVariable, n);
            lastValue = myChildren.get(myChildren.size() - 1).getValue();
        }
        
        return lastValue;
    }
    
    protected abstract void setVariables() throws IllegalArgumentException, ParseException;
}