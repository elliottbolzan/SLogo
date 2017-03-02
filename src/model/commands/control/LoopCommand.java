package model.commands.control;

import java.util.ArrayList;

import model.StateStorage;
import model.Variable;

/*
public abstract class LoopCommand extends Command {
	
	protected StateStorage s;
	protected String varName;
	protected int start;
	protected int increment;
	protected int end;

	public LoopCommand(StateStorage store, String var) {
		super(new ArrayList<Command>());
		s = store;
		varName = var;
	}

	@Override
	public void execute() {
		setVar();
		loop();
	}

	private double loop() {
		int previous = 0;
		for (int k = start; k <= end; k += increment) {
			s.setVariable(new Variable(varName, k));
			//previous = (int) cmdTree.get(cmdTree.size() - 1).execute();
		}

		return previous;
	}
	
	public double getReturnValue() {
		return 0;
	}

	protected abstract void setVar();
	
}*/