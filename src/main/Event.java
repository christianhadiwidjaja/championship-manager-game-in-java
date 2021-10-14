package main;

import java.io.Serializable;
import java.util.List;

public class Event implements Serializable {
	
	private Player actionPlayer;
	private Player newPlayer;
	private Action action;
	private List<String> comments;
	private boolean isSuccessful;
	private int minutes;
	
	public boolean isSuccessful() {
		return isSuccessful;
	}
	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	public void setActionPlayer(Player actionPlayer) {
		this.actionPlayer = actionPlayer;
	}
	public void setNewPlayer(Player newPlayer) {
		this.newPlayer = newPlayer;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public Player getActionPlayer() {
		return actionPlayer;
	}
	public Player getNewPlayer() {
		return newPlayer;
	}
	public Action getAction() {
		return action;
	}
	public List<String> getComments() {
		return comments;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
}
