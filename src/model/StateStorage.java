package model;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StateStorage {

	private HashMap<String, UserCommand> cmdList;

	private ObservableList<Variable> variables;

	public StateStorage() {
		variables = FXCollections.observableArrayList(new ArrayList<Variable>());
		cmdList = new HashMap<String, UserCommand>();
	}

	public void setVariable(Variable var) {
		int index = getVariableIndex(var);
		if (!(index == -1)) {
			variables.remove(index);
		}
		variables.add(var);
	}

	public void setCommand(UserCommand cmd) {
		cmdList.put(cmd.getName(), cmd);
	}

	public ObservableList<Variable> getVariables() {
		return variables;
	}

	public HashMap<String, UserCommand> getCmdList() {
		return cmdList;
	}
	
	private int getVariableIndex(Variable var) {
		int index = -1;
		for (Variable existing: variables) {
			if (existing.getName().equals(var.getName())) {
				index = variables.indexOf(existing);
				break;
			}
		}
		return index;
	}
	
}