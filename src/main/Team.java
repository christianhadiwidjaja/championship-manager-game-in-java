package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Serializable {
	
	private List<Player> listOfPlayers = new ArrayList<>();
	private List<Event> goalEvents = new ArrayList<>();
	private String teamName;
	private String flag;

	public Team() {
		
	}
	
	public Player getStartingPlayer() {
		//asumsikan starting player selalu defender agar tidak langsung terjadi gol.
		return listOfPlayers.get(1);
	}

	public Player getRandomPlayer() {
		return RandomUtil.getRandomPlayer(listOfPlayers);
	}

	public Player getRandomPlayer(Player excludePlayer) {
		List<Player> randomPlayerList = new ArrayList(listOfPlayers);
		randomPlayerList.remove(excludePlayer);

		return RandomUtil.getRandomPlayer(randomPlayerList);
	}

	public Player getGoalkeeper() {
		//asumsikan index ke 0 selalu goalkeeper
		return listOfPlayers.get(0);
	}
	
	public List<Player>getPlayers(){
		return listOfPlayers;
	}

	public void addPlayer(Player player) {
		listOfPlayers.add(player);
	}
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public void scoreGoal(Event goalEvent){
		goalEvents.add(goalEvent);
	}

	public int getScore() {
		return goalEvents.size();
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<Event> getGoalEvents() {
		return goalEvents;
	}
}
