package model;

public class DotimesCommand extends LoopCommand{
	public DotimesCommand(StateStorage store, String var) {
		super(store, var);
	}

	public double execute(String varName, double limit, String[] commands){
		return 0;
	}

	@Override
	protected void setVariables() {
		 start = 0;
	     //end = myChildren.get(0).execute();
	     increment = 1;		
	}

	@Override
	public int getNumOfArguments() {
		return 2;
	}
}
