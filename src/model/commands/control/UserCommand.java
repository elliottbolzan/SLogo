package model.commands.control;

import java.util.ArrayList;

import model.State;
import model.commands.Command;
import model.parser.Argument;
import utils.BadInputException;

public class UserCommand extends Command {
	
	private String name;
	private ArrayList<String> variableNames;
	private String command;
	private State storage;
	
	public UserCommand(String name, ArrayList<String> variableNames, String command, State storage) {
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
	public Argument getReturnValue() {
		return 0;
	}

	@Override
	public Argument execute() throws BadInputException {
		String currentCommand = command;
		for (String variableName: variableNames) {
			try {
				currentCommand = currentCommand.replaceAll(variableName, getParameterList().get(variableNames.indexOf(variableName)).toString());
			} catch (Exception e) {
				throw new BadInputException("Variable was not instantiated: " + variableName);
			} 
		}
		try {
			getController().parse(currentCommand);
		}
		catch (Exception e) {
			getController().getView().showMessage(e.getMessage());
		}
	}


	public String getName() {
		return name;
	}
	
}