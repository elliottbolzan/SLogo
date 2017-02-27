package model.commands;

import controller.Controller;
import view.Turtle;

public interface CommandInterface {

	public String toString();

	public int numParameters();
	
	public double execute(int[] parameters, Turtle myTurtle, Controller view);
}
