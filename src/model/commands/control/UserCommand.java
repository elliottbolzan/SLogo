package model.commands.control;

import java.util.ArrayList;

import model.StateStorage;
import model.commands.Command;

public class UserCommand extends Command {
	
	private String name;
	private ArrayList<String> variableNames;
	private String command;
	private StateStorage storage;
	
	public UserCommand(String name, ArrayList<String> variableNames, String command, StateStorage storage) {
		this.name = name;
		this.variableNames = variableNames;
		this.command = command.trim();
		this.storage = storage;
		this.storage.setCommand(this);
	}
	
	public UserCommand() {}

	@Override
	public int numParameters() {
		return variableNames.size();
	}

	@Override
	public double getReturnValue() {
		return 0;
	}

	@Override
	public void execute() {
		String currentCommand = command;
		for (String variableName: variableNames) {
			currentCommand = currentCommand.replaceAll(variableName, getParameterList().get(variableNames.indexOf(variableName)).toString()); 
		}
		getController().parse(currentCommand);
	}


	public String getName() {
		return name;
	}
	
}