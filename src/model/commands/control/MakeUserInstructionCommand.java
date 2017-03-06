package model.commands.control;

import java.util.ArrayList;

import model.State;
import model.commands.Command;
import model.parser.Argument;
import utils.BadInputException;

public class MakeUserInstructionCommand extends Command {
	
	private String name;
	private ArrayList<String> variableNames;
	private String command;
	private State storage;
	
	public MakeUserInstructionCommand(String name, ArrayList<String> variableNames, String command, State storage) {
		this.name = name;
		this.variableNames = variableNames;
		this.command = command.trim();
		this.storage = storage;
		this.storage.setCommand(this);
	}
	
	public MakeUserInstructionCommand() {}

	@Override
	public int numParameters() {
		return variableNames.size();
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
			return new Argument(1);
		}
		catch (Exception e) {
			getController().getView().showMessage(e.getMessage());
			return new Argument(0);
		}
	}


	public String getName() {
		return name;
	}
}