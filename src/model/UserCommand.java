package model;

public class UserCommand{
	private String name;
	private String[] commands;
	private int myParameters;
	
	public UserCommand(String cmdName, String[] cmdList){
		name = cmdName;
		commands = cmdList;
		
		myParameters = cmdList.length;
	}
	
	public int execute(){
		//need parser for user created commands
		for(String cmds: commands){
		}
		return myParameters;
	}
	
	public String getName(){
		return name;
	}
}