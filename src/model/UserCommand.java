package model;

public class UserCommand {
	private String name;
	private String[] commands;
	private String[] variables;
	private int myParameters;

	public UserCommand(String cmdName, String[] varList, String[] cmdList) {
		name = cmdName;
		commands = cmdList;
		variables = varList;

		myParameters = cmdList.length;
	}

	public double execute(String cmd, String[] var, String[] cmdList, StateStorage s) {
		s.setCommand(new UserCommand(name, variables, commands));
		return 1;
	}

	public String getName() {
		return name;
	}
}