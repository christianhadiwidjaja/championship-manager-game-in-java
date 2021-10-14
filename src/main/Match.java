package main;

import java.io.Serializable;
import java.util.Arrays;

public class Match implements Serializable {
	
	private int minutes;
	private int seconds;
	private Team team1;
	private Team team2;
	private Player playerWithBall;

	private transient MatchListener matchListener;

	public Match(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
	}
	
	public Player getPlayerWithBall() {
		return playerWithBall;
	}

	public void setPlayerWithBall(Player playerWithBall) {
		this.playerWithBall = playerWithBall;
	}
	
	public void startMatch(){
		setPlayerWithBall(RandomUtil.getRandomTeam(team1, team2).getStartingPlayer());
		Thread matchThread = new Thread(new MatchRunnable());
		matchThread.start();

		Thread timerThread = new Thread(new TimerRunnable());
		timerThread.start();
	}

	public void setMatchListener(MatchListener matchListener) {
		this.matchListener = matchListener;
	}

	private Team getOpposingTeam(Player player) {
		if(player.getTeam().equals(team1)){
			return team2;
		}
		else{
			return team1;
		}
	}

	public Team getTeam1() {
		return team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public boolean isMatchOn() {
		return minutes < 90;
	}

	private class TimerRunnable implements Runnable{

		@Override
		public void run() {
			while(isMatchOn()){
				tick();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		private void tick(){
			seconds++;
			if(seconds==60){
				seconds = 0;
				minutes++;
			}

			if (matchListener != null) {
				matchListener.onTick(minutes, seconds);
			}
		}
	}
	
	private class MatchRunnable implements Runnable{
		
		@Override
		public void run() {
			while(isMatchOn()){
				Action action = playerWithBall.generateAction();
				publishAction(action);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Event event = action.getOutcomeEvent(getOpposingTeam(action.getActionPlayer()), minutes);
				setPlayerWithBall(event.getNewPlayer());
				publishEvent(event);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			Event event = generateFinishEvent();
			publishEvent(event);
		}

		private Event generateFinishEvent() {
			Event event = new Event();
			event.setMinutes(minutes);
			event.setComments(Arrays.asList("Match Finished"));

			return event;
		}

		private void publishAction(Action action) {
			if (matchListener != null) {
				matchListener.onAction(action);
			}
		}

		private void publishEvent(Event event) {
			if (matchListener != null) {
				matchListener.onEvent(event);
			}
		}
	}
}
