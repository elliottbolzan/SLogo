package model.commands;

import java.util.List;

import controller.Controller;

public abstract class Command implements CommandInterface {

	private Controller myController;
	private List<Double> myParameters;
	
	public void initialize(List<Double> params, Controller control) {
		myController = control;
		myParameters = params;
	}
	
	protected Controller getController() {
		return myController;
	}
	
	protected List<Double> getParameterList() {
		return myParameters;
	}
	
}
