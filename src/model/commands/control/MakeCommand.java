package model.commands.control;

import java.util.ArrayList;

import model.StateStorage;

public class MakeCommand extends ControlCommand {
	
    public MakeCommand (StateStorage store, String var) {
    	super(new ArrayList<ControlCommand>());
    }
    
	@Override
	public double execute(String cmd, String[] varList, String[] cmdList, double expr, StateStorage s) {
		s.setCommand(new UserCommand(cmd, varList, cmdList));
		return 1;
	}

	@Override
	public int getNumOfArguments() {
		return 2;
	}
}
