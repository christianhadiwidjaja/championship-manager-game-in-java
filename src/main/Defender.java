package main;

public class Defender extends Player{

	public Defender(Team team) {
		super(team);
	}

	@Override
	public String getPlayerPosition() {
		return "DF";
	}

	@Override
	public int[] getActionProbabilities(Action[] actions) {
		//hold, short pass, long pass, shoot
		return new int[] {
				2, 4, 5, 1
		};
	}
}
