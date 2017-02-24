package model;

import controller.Controller;

public class Model implements ModelAPI {

	private Controller controller;
	
	public Model(Controller controller) {
		this.controller = controller;
	}
	
	public Command parse(String string) {
		return new Command();
	}

}
