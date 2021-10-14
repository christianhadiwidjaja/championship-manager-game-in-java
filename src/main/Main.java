package main;

import file.MatchFile;
import ui.MainWindow;

import java.util.Arrays;
import java.util.List;

public class Main {

	public Main() {
	}

	public static void main(String[] args) {
		List<Team> teams = createTeams();

		MainWindow window = new MainWindow(teams, new MatchFile("match.dat")); //save as match.dat
		window.show();
	}

	private static List<Team> createTeams() {
		return Arrays.asList(
				createIndonesiaTeam(),
				createBrazilTeam(),
				createEnglandTeam(),
				createSpainTeam()
		);
	}

	private static Team createIndonesiaTeam() {
		Team team = new Team();
		team.setTeamName("Indonesia");
		team.addPlayer(createGoalKeeper(team, 26, "Andritany Ardhiyasa", 8));
		team.addPlayer(createDefender(team, 16, "Fachrudin Aryanto", 9));
		team.addPlayer(createDefender(team, 15, "Ricky Fajrin", 10));
		team.addPlayer(createDefender(team, 17, "Andy Setyo", 9));
		team.addPlayer(createDefender(team, 18, "Fachruddin", 9));
		team.addPlayer(createMidfielder(team, 14, "Rizky Pora", 11));
		team.addPlayer(createMidfielder(team, 13, "Rachmat Irianto", 10));
		team.addPlayer(createMidfielder(team, 11, "Bayu Pradana", 8));
		team.addPlayer(createMidfielder(team, 10, "Septian David", 8));
		team.addPlayer(createForward(team, 27, "Dedik Setiawan", 7));
		team.addPlayer(createForward(team, 7, "M.Rafli", 10));
		team.setFlag("indonesia.png");

		return team;
	}

	private static Team createBrazilTeam() {
		Team team = new Team();
		team.setTeamName("Brazil");
		team.addPlayer(createGoalKeeper(team, 23, "Ederson", 12));
		team.addPlayer(createDefender(team, 6, "Marquinhos", 13));
		team.addPlayer(createDefender(team, 4, "Dani Alves", 15));
		team.addPlayer(createDefender(team, 3, "Robinho", 15));
		team.addPlayer(createDefender(team, 33, "Marcelo", 16));
		team.addPlayer(createMidfielder(team, 5, "Casemiro", 14));
		team.addPlayer(createMidfielder(team, 13, "Fernandinho", 14));
		team.addPlayer(createMidfielder(team, 11, "Philippe Coutinho", 17));
		team.addPlayer(createMidfielder(team, 12, "Willian", 17));
		team.addPlayer(createForward(team, 10, "Neymar", 19));
		team.addPlayer(createForward(team, 34, "Gabriel Jesus", 18));
		team.setFlag("brazil.png");

		return team;
	}

	private static Team createEnglandTeam() {
		Team team = new Team();
		team.setTeamName("England");
		team.addPlayer(createGoalKeeper(team, 1, "Nick Pope", 12));
		team.addPlayer(createDefender(team, 2, "Alexander Arnold", 13));
		team.addPlayer(createDefender(team, 3, "Ben Chilwell", 15));
		team.addPlayer(createDefender(team, 5, "Harry Maguire", 14));
		team.addPlayer(createDefender(team, 15, "John Stones", 16));
		team.addPlayer(createMidfielder(team, 8, "Oxlade Chamberlain", 14));
		team.addPlayer(createMidfielder(team, 18, "James Maddison", 14));
		team.addPlayer(createMidfielder(team, 10, "Harry Winks", 17));
		team.addPlayer(createMidfielder(team, 17, "Jordan Henderson", 17));
		team.addPlayer(createForward(team, 9, "Harry Kane", 19));
		team.addPlayer(createForward(team, 7, "Raheem Sterling", 17));
		team.setFlag("england.png");

		return team;
	}

	private static Team createSpainTeam() {
		Team team = new Team();
		team.setTeamName("Spain");
		team.addPlayer(createGoalKeeper(team, 1, "De Gea", 19));
		team.addPlayer(createDefender(team, 18, "Jordi Alba", 15));
		team.addPlayer(createDefender(team, 3, "Piqué", 18));
		team.addPlayer(createDefender(team, 15, "Sergio Ramos", 18));
		team.addPlayer(createDefender(team, 14, "Azpilicueta", 16));
		team.addPlayer(createMidfielder(team, 21, "David Silva", 18));
		team.addPlayer(createMidfielder(team, 8, "Iniesta", 18));
		team.addPlayer(createMidfielder(team, 20, "Marco Ascensio", 17));
		team.addPlayer(createMidfielder(team, 10, "Thiago", 17));
		team.addPlayer(createForward(team, 19, "Diego Costa", 17));
		team.addPlayer(createForward(team, 9, "Rodrigo", 15));
		team.setFlag("spain.png");

		return team;
	}

	private static Player createGoalKeeper(
			Team team,
			int number,
			String name,
			int skillPoint) {

		Player player = new Goalkeeper(team);
		player.setNumber(number);
		player.setPlayerName(name);
		player.setPlayerSkillPoint(skillPoint);

		return player;
	}

	private static Player createDefender(
			Team team,
			int number,
			String name,
			int skillPoint) {

		Player player = new Defender(team);
		player.setNumber(number);
		player.setPlayerName(name);
		player.setPlayerSkillPoint(skillPoint);

		return player;
	}

	private static Player createMidfielder(
			Team team,
			int number,
			String name,
			int skillPoint) {

		Player player = new Midfielder(team);
		player.setNumber(number);
		player.setPlayerName(name);
		player.setPlayerSkillPoint(skillPoint);

		return player;
	}

	private static Player createForward(
			Team team,
			int number,
			String name,
			int skillPoint) {

		Player player = new Forward(team);
		player.setNumber(number);
		player.setPlayerName(name);
		player.setPlayerSkillPoint(skillPoint);

		return player;
	}
}
