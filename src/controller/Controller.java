package controller;

import java.awt.Dimension;
import utils.Point;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.Model;
import model.ModelAPI;
import model.Variable;
import view.Turtle;
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
		model = new Model(this);
		view = new View(this, stage);
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
	public Turtle getTurtle() {
		return view.getTurtle();
	}
	
	@Override
	public void parse(String string) {
		model.parse(string);
	}

	@Override
	public ObservableList<String> getHistory() {
		return model.getHistory();
	}

	@Override
	public ObservableList<Variable> getVariables() {
		return model.getVariables();
	}

	@Override
	public ObservableList<String> getUserDefinedCommands() {
		return model.getUserDefinedCommands();
	}
	
	@Override
	public void setLanguage(String language) {
		model.setLanguage(language);
	}

}
