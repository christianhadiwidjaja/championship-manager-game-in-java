package main;

import java.util.Arrays;
import java.util.List;

public class Shoot extends AbstractAction {

	public Shoot(Player actionPlayer, Player targetPlayer) {
		super(actionPlayer, targetPlayer);
	}

	@Override
	public List<String> getComments() {
		String comment = getActionPlayer().getPlayerName() + " Shoots.";
		return Arrays.asList(comment);
	}

	@Override
	public double getActionProbability(Team opposingTeam) {
		Player player = getActionPlayer();
		double shootProbability;
		if(player instanceof Forward){
			shootProbability = 0.65 + player.getPlayerSkillPoint()/20 * 0.05;
		}
		else{
			shootProbability = 0.35 + player.getPlayerSkillPoint()/20 * 0.05;
		}

		double saveProbability = opposingTeam.getGoalkeeper().getPlayerSkillPoint()/20 * 0.15;
		return shootProbability - saveProbability;
	}

	@Override
	public void completeEventFields(Event event, Team opposingTeam) {
		Player newPlayer;
		// jika success berarti goal dan kickoff, bola dikaki starting player musuh
		// jika gagal berarti bola dikaki musuh
		if (event.isSuccessful()){
			getActionPlayer().getTeam().scoreGoal(event);
			newPlayer = opposingTeam.getStartingPlayer();
		}
		else{
			newPlayer = opposingTeam.getRandomPlayer();
		}

		event.setComments(getEventComments(event.isSuccessful(), newPlayer));
		event.setNewPlayer(newPlayer);
	}

	private List<String> getEventComments(boolean isSuccessful, Player newPlayer) {
		if (isSuccessful){
			return Arrays.asList("GOAL!!! ",
					"A very beautiful goal from " + getActionPlayer().getPlayerName());
		}
		else{
			return Arrays.asList("Oh so unfortunate! ",
					getActionPlayer().getPlayerName() + " fails to score");
		}
	}
}
