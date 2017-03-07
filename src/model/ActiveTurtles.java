package model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ActiveTurtles {

	private ObservableList<Integer> activeList;

	public ActiveTurtles() {
		activeList = FXCollections.observableArrayList(new ArrayList<Integer>());
	}

	public void addTurtles(Integer[] add) {
		for(Integer addActive: add){
			if(!activeList.contains(addActive)){
				activeList.add(addActive);
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

	public ObservableList<Integer> getActiveTurtles(){
		return activeList;
	}
}