package main;

import java.util.List;

public interface Action {
	
	public Event getOutcomeEvent(Team opposingTeam, int minutes);
	public Player getActionPlayer();
	public Player getTargetPlayer();
	public List<String> getComments();
	
}
