package main;

import java.util.Arrays;
import java.util.List;

public class ShortPass extends AbstractAction {

	public ShortPass(Player actionPlayer, Player targetPlayer) {
		super(actionPlayer, targetPlayer);
	}

	@Override
	public List<String> getComments() {
		String comment = getActionPlayer().getPlayerName() + " passes to " + getTargetPlayer().getPlayerName();
		return Arrays.asList(comment);
	}

	@Override
	public double getActionProbability(Team opposingTeam) {
		Player player = getActionPlayer();
		if(player instanceof Midfielder){
			double probability = 0.65 + player.getPlayerSkillPoint()/20 * 0.05;
			return probability;
		}
		else{
			double probability = 0.6 + player.getPlayerSkillPoint()/20 * 0.05;
			return probability;
		}
	}

	@Override
	public void completeEventFields(Event event, Team opposingTeam) {
		Player newPlayer;
		if (event.isSuccessful()){
			newPlayer = getTargetPlayer();
		}
		else{
			newPlayer = opposingTeam.getRandomPlayer();
		}

		event.setComments(getEventComments(event.isSuccessful(), newPlayer));
		event.setNewPlayer(newPlayer);
	}

	private List<String> getEventComments(boolean isSuccessful, Player newPlayer) {
		String comment;
		if (isSuccessful){
			comment = "The ball is in " + newPlayer.getPlayerName();
		}
		else{
			comment = "The ball is stolen by " + newPlayer.getPlayerName();
		}
		return Arrays.asList(comment);
	}
}
