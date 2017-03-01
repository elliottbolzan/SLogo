package model;

public class UserCommand{
	private String name;
	private String[] commands;
	private String[] variables;
	private int myParameters;
	
	public UserCommand(String cmdName, String[] varList, String[] cmdList){
		name = cmdName;
		commands = cmdList;
		variables = varList;
		
		myParameters = cmdList.length;
	}
	
	public double execute(String cmd, String[] var, String[] cmdList, StateStorage s){
		//need parser for user created commands
		for(String cmds: commands){
			//prase cmds to execute them
		}
		return myParameters;
	}
	
	public String getName(){
		return name;
	}
}