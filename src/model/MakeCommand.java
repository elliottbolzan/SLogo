package model;

import controller.Controller;
import model.commands.Command;
import view.visualization.Turtle;

public class MakeCommand extends UserCommand {
	
	public MakeCommand(String name, String[] varList, String[] cmdList) {
		super(name, varList, cmdList);
	}

	@Override
	public double execute(String cmd, String[] varList, String[] cmdList, StateStorage s) {
		s.getCmdList();
		return 0;
	}
}
