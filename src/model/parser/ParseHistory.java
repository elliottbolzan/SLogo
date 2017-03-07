package model.parser;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ParseHistory {

	private ObservableList<String> historyList;
	
	public ParseHistory(){
		historyList = FXCollections.observableList(new ArrayList<String>());
	}

	public ObservableList<String> getHistoryList() {
		return historyList;
	}

	public void setHistoryList(ObservableList<String> historyList) {
		this.historyList = historyList;
	}
	
	public void clearHistoryList(){
		setHistoryList(FXCollections.observableList(new ArrayList<String>()));
	}
	
	public void addStringToHistory(String s){
		historyList.add(0, s);
	}
	
}
