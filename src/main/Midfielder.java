package main;

public class Midfielder extends Player{

	public Midfielder(Team team) {
		super(team);
	}

	@Override
	public String getPlayerPosition() {
		return "MF";
	}

	@Override
	public int[] getActionProbabilities(Action[] actions) {
		//hold, short pass, long pass, shoot
		return new int[] {
			2, 5, 5, 3
		};
	}

}
