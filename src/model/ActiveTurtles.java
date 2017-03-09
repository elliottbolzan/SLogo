package model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ActiveTurtles {

	private ObservableList<Integer> activeList;
	private int totalTurtles;
	private int index;

	public ActiveTurtles() {
		totalTurtles = 0;
		activeList = FXCollections.observableArrayList(new ArrayList<Integer>());
		activeList.add(1);
		index = 0;
	}

	public void addTurtles(Integer[] add) {
		for(Integer addActive: add){
			if(!activeList.contains(addActive)){
				activeList.add(addActive);
				totalTurtles++;
			}
		}
	}

	public void setTurtles(Integer[] set){
		activeList.clear();
		this.addTurtles(set);
	}
	
	public void removeTurtles(Integer[] remove){
		for(Integer addActive: remove){
			if(activeList.contains(addActive)){
				activeList.remove(addActive);
			}
		}
	}

	public ObservableList<Integer> getTurtleList(){
		return activeList;
	}
	
	public int getTotalTurtles(){
		return totalTurtles;
	}
	
	public int getIndex(){
		return index;
	}
	
	public void setIndex(int add){
		index=add;
	}
}