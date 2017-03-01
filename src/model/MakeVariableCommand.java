package model;

import controller.Controller;
import model.commands.Command;
import view.visualization.Turtle;

public class MakeVariableCommand{
	public double execute(String name, double expr, StateStorage s) {
		s.setVariable(new Variable(name, expr));
		return expr;
	}
}
