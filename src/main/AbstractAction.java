package main;

import java.io.Serializable;

public abstract class AbstractAction implements Action, Serializable {

	private Player actionPlayer;
	private Player targetPlayer;

	public AbstractAction(Player actionPlayer, Player targetPlayer) {
		this.actionPlayer = actionPlayer;
		this.targetPlayer = targetPlayer;
	}

	@Override
	public Event getOutcomeEvent(Team opposingTeam, int minutes) {
		Event event = new Event();
		event.setAction(this);
		event.setActionPlayer(actionPlayer);
		event.setMinutes(minutes);

		double probability = getActionProbability(opposingTeam);
		boolean isSuccessful = RandomUtil.successfulOrNot(new Fraction(probability));
		event.setSuccessful(isSuccessful);

		completeEventFields(event, opposingTeam);
		return event;
	}

	@Override
	public Player getActionPlayer() {
		return actionPlayer;
	}

	@Override
	public Player getTargetPlayer() {
		return targetPlayer;
	}

	public abstract void completeEventFields(Event event, Team opposingTeam);
	public abstract double getActionProbability(Team opposingTeam);
}
