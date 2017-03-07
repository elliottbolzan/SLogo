package model.parser;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.parser.nodes.Node;

public class ParseHistory {

	private ObservableList<String> historyList;
	private List<Node> commandList;
	
	public ParseHistory(){
		historyList = FXCollections.observableList(new ArrayList<String>());
		setCommandList(new ArrayList<Node>());
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

	public List<Node> getCommandList() {
		return commandList;
	}

	public void setCommandList(List<Node> commandList) {
		this.commandList = commandList;
	}
	
	public void addCommandToHistory(Node node){
		commandList.add(0, node);
	}
	
}
