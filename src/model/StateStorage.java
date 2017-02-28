package model;

import java.util.HashMap;

public class StateStorage{
	
	private HashMap<String, Variable> varList;
	private HashMap<String, UserCommand> cmdList;
	
	public StateStorage(){
		varList = new HashMap<String, Variable>();
		cmdList = new HashMap<String, UserCommand>();
	}
	
	public void setVariable(Variable var){
		varList.put(var.getName(), var);
	}
	
	public void setCommand(UserCommand cmd){
		cmdList.put(cmd.getName(), cmd);
	}
	
	public HashMap<String, Variable> getVarList(){
		return varList;
	}
	
	public HashMap<String, UserCommand> getCmdList(){
		return cmdList;
	}
}