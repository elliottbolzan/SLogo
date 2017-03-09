package model;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import model.commands.control.MakeUserInstructionCommand;

public class State {

	private HashMap<String, MakeUserInstructionCommand> cmdList;

	private ObservableList<Variable> variables;
	private ObservableList<String> userDefinedCommandNames;
	private ObservableList<IndexedColor> colors;
	private ObservableList<IndexedImage> images;
	

	public State() {
		variables = FXCollections.observableArrayList(new ArrayList<Variable>());
		userDefinedCommandNames = FXCollections.observableArrayList(new ArrayList<String>());
		colors = FXCollections.observableArrayList(new ArrayList<IndexedColor>());
		images = FXCollections.observableArrayList(new ArrayList<IndexedImage>());
		cmdList = new HashMap<String, MakeUserInstructionCommand>();
		
		makeDefaultColors();
		makeDefaultImages();
	}

	public void setVariable(Variable var) {
		int index = getVariableIndex(var);
		if (!(index == -1)) {
			variables.remove(index);
		}
		variables.add(var);
	}

	public void setCommand(MakeUserInstructionCommand cmd) {
		userDefinedCommandNames.add(cmd.getName());
		cmdList.put(cmd.getName(), cmd);
	}

	public ObservableList<Variable> getVariables() {
		return variables;
	}
	
	public double getVariableValue(String name) {
		for (Variable variable: variables) {
			if (variable.getName().equals(name)) {
				return Double.parseDouble(variable.getValue());
			}
		}
		return 0;
	}

	public ObservableList<String> getUserDefinedCommands() {
		return userDefinedCommandNames;
	}
	
	public HashMap<String, MakeUserInstructionCommand> getCmdList() {
		return cmdList;
	}
	
	public int getVariableIndex(Variable var) {
		int index = -1;
		for (Variable existing: variables) {
			String name = var.getName().replace(":", "");
			if (existing.getName().equals(name)) {
				index = variables.indexOf(existing);
				break;
			}
		}
		return index;
	}
	
	public ObservableList<IndexedColor> getColorPalette() {
		return colors;
	}
	
	private void makeDefaultColors() {
		colors.add(new IndexedColor(1, Color.BLACK));
		colors.add(new IndexedColor(2, Color.WHITE));
		colors.add(new IndexedColor(3, Color.RED));
		colors.add(new IndexedColor(4, Color.ORANGE));
		colors.add(new IndexedColor(5, Color.YELLOW));
		colors.add(new IndexedColor(6, Color.GREEN));
		colors.add(new IndexedColor(7, Color.BLUE));
		colors.add(new IndexedColor(8, Color.PURPLE));
		colors.add(new IndexedColor(9, Color.GOLD));
		colors.add(new IndexedColor(10, Color.SILVER));
	}
	
	public ObservableList<IndexedImage> getImagePalette() {
		return images;
	}
	
	private void makeDefaultImages() {
		images.add(new IndexedImage(1, "resources/images/turtle_1.png"));
		images.add(new IndexedImage(2, "resources/images/turtle_2.png"));
		images.add(new IndexedImage(3, "resources/images/turtle_3.png"));
		images.add(new IndexedImage(4, "resources/images/turtle_4.png"));
		images.add(new IndexedImage(5, "resources/images/turtle_5.png"));
	}
}