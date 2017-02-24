package controller;

import java.awt.Dimension;
import utils.Point;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.Command;
import model.Model;
import model.ModelAPI;
import model.Variable;
import view.View;
import view.ViewAPI;

/**
 * @author Elliott Bolzan
 *
 */
public class Controller implements ViewAPI, ModelAPI {
	
	private View view;
	private Model model;

	/**
	 * 
	 */
	public Controller(Stage stage) {
		view = new View(this, stage);
		model = new Model(this);
	}

	@Override
	public void print(String string) {
		view.print(string);
	}

	@Override
	public void clearConsole() {
		view.clearConsole();
	}

	@Override
	public void moveTo(Point point) {
		view.moveTo(point);
	}

	@Override
	public void turn(double degrees) {
		view.turn(degrees);
	}

	@Override
	public void setPenDown(boolean down) {
		view.setPenDown(down);
	}

	@Override
	public void setTurtleVisible(boolean visible) {
		view.setTurtleVisible(visible);	
	}

	@Override
	public void clearDisplay() {
		view.clearDisplay();
	}

	@Override
	public Dimension getDisplaySize() {
		return view.getDisplaySize();
	}
	
	@Override
	public Command parse(String string) {
		return model.parse(string);
	}

	@Override
	public ObservableList<String> getHistory() {
		return null;
	}

	@Override
	public ObservableList<Variable> getVariables() {
		return null;
	}

	@Override
	public ObservableList<String> getUserDefinedCommands() {
		return null;
	}

}
