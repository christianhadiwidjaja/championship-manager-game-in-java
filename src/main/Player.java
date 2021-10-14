package main;

import java.io.Serializable;

public abstract class Player implements Serializable {
	
	private String playerName;
	private int playerSkillPoint;
	private int number;
	private Team team;

	public Player(Team team) {
		this.team = team;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getPlayerSkillPoint() {
		return playerSkillPoint;
	}

	public void setPlayerSkillPoint(int playerSkillPoint) {
		this.playerSkillPoint = playerSkillPoint;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}


	public Action generateAction() {
		Action[] actions = new Action[] {
				new Hold(this, this),
				new ShortPass(this, team.getRandomPlayer(this)),
				new LongPass(this, team.getRandomPlayer(this)),
				new Shoot(this, null)
		};

		return RandomUtil.getRandomActions(actions, getActionProbabilities(actions));
	}

	public abstract String getPlayerPosition();
	public abstract int[] getActionProbabilities(Action[] actions);
}
