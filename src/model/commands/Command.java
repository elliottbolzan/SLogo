package model.commands;

import java.util.List;

import controller.Controller;
import model.State;
import model.parser.Argument;
import model.parser.nodes.Node;
import utils.BadInputException;

public abstract class Command extends Node implements CommandInterface {

	private Controller myController;
	private List<Argument> myParameters;
	private State myState;
	
	public void initialize(List<Argument> params, Controller control, State state) {
		myController = control;
		myParameters = params;
		myState = state;
	}

	protected Controller getController() {
		return myController;
	}

	protected List<Argument> getParameterList() {
		return myParameters;
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
		} catch (BadInputException e) {
			e.printStackTrace();
		}
		return new Argument();
	}
	
}
