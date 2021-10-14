package main;

public class Forward extends Player{

	public Forward(Team team) {
		super(team);
	}

	@Override
	public String getPlayerPosition() {
		return "FW";
	}

	@Override
	public int[] getActionProbabilities(Action[] actions) {
		//hold, short pass, long pass, shoot
		return new int[] {
				2, 4, 2, 5
		};
	}
}
