package main;

public class Goalkeeper extends Player{

	public Goalkeeper(Team team) {
		super(team);
	}

	@Override
	public String getPlayerPosition() {
		return "GK";
	}

	@Override
	public int[] getActionProbabilities(Action[] actions) {
		//hold, short pass, long pass, shoot
		return new int[] {
				2, 2, 5, 0
		};
	}
}
