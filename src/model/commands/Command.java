package model.commands;

import java.util.List;

import controller.Controller;
import model.State;
import model.parser.Argument;
import model.parser.nodes.Node;

public abstract class Command extends Node {

	private Controller myController;
	private State myState;

	protected Controller getController() {
		return myController;
	}

	protected State getState() {
		return myState;
	}
	
	public void setup(Controller controller, State state) {
		this.myController = controller;
		this.myState = state;
	}

	public Argument evaluate() {
		try {
			return execute();
		} catch (Exception e) {
			getController().getView().showMessage("Could not evaluate command.");;
		}
		return new Argument();
	}
	
	protected abstract Argument execute();
	
	protected abstract int internalNumParameters();
	
	public int numParameters() {
		return internalNumParameters();
	}
	
}
