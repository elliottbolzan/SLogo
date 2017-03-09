package model.commands.control;

import java.util.ArrayList;

import model.State;
import model.commands.Command;
import model.parser.Argument;

public class MakeUserInstructionCommand extends Command {
	
	private String name;
	private ArrayList<String> variableNames;
	private String command;
	private State storage;
	
	public MakeUserInstructionCommand(String name, ArrayList<String> variableNames, String command, State storage) {
	}
	
	public MakeUserInstructionCommand() {}

	@Override
	public int numParameters() {
		return 3;
	}

	@Override
	public Argument execute(){
		String currentCommand = command;
		
		
		
		for (String variableName: variableNames) {
			try {
				currentCommand = currentCommand.replaceAll(variableName, getParameterList().get(variableNames.indexOf(variableName)).toString());
			} catch (Exception e) {
				getController().getView().showMessage("Variable was not instantiated: " + variableName);
			} 
		}
		try {
			getController().parse(currentCommand, false);
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