package model.parser;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ParseHistory {

	private ObservableList<String> historyList;

	protected ParseHistory() {
		historyList = FXCollections.observableList(new ArrayList<String>());
	}

	protected ObservableList<String> getHistoryList() {
		return historyList;
	}

	protected void addStringToHistory(String s) {
		historyList.add(0, s);
	}

}
