package model.parser;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.parser.nodes.Node;

public class ParseHistory {

	private ObservableList<String> historyList;
	private List<Node> commandList;
	private ArrayList<String> userMadeCommandNames;
	private ArrayList<Node> userMadeCommandNodes;
	
	public ParseHistory(){
		historyList = FXCollections.observableList(new ArrayList<String>());
		setCommandList(new ArrayList<Node>());
		userMadeCommandNames = new ArrayList<String>();
		userMadeCommandNodes = new ArrayList<Node>();
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

	public String getUserMadeCommandName(int i) {
		return userMadeCommandNames.get(i);
	}

	public void addUserMadeCommandName(String s) {
		this.userMadeCommandNames.add(s);
	}

	public Node getUserMadeCommandNode(int i) {
		return userMadeCommandNodes.get(i);
	}

	public void addUserMadeCommandNode(Node n) {
		this.userMadeCommandNodes.add(n);
	}
	
	public Node getCommand(String s){
		if(userMadeCommandNames.contains(s)){
			return userMadeCommandNodes.get(userMadeCommandNames.indexOf(s));
		}
		return null;
	}
	
	public boolean isNewCommand(String s){
		if(userMadeCommandNames.contains(s)) return true;
		return false;
	}
	
}
