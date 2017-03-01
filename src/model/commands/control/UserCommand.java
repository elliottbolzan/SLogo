package model.commands.control;

import java.util.ArrayList;

import model.StateStorage;

public class UserCommand extends ControlCommand{
	protected UserCommand(ArrayList<ControlCommand> previousTree) {
		super(previousTree);
	}

	private String name;
	private String[] commands;
	private String[] variables;
	private int myParameters;

//	public UserCommand() {
//		name = cmdName;
//		commands = cmdList;
//		variables = varList;
//
//		myParameters = cmdList.length;
//	}

	public double execute(String cmd, String[] var, String[] cmdList, StateStorage s) {
		//s.setCommand(new UserCommand(name, variables, commands));
		return 1;
	}

	public String getName() {
		return name;
	}

	@Override
	protected double execute() {
		return 0;
	}

	@Override
	public int numParameters() {
		return 0;
	}
}