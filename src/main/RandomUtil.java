package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

	// dibuat constructor private supaya tidak ada yang new.
	private RandomUtil() {

	}

	// lihat dari dokumentasi Random, dikatakan bahwa sebaiknya menggunakan ThreadLocalRandom jika
	// menggunakan multithreading.
	public static Team getRandomTeam(Team... teams){
		Random random = ThreadLocalRandom.current();
		if(teams.length == 0){
			return null;
		}
		else{
			int randIdx = random.nextInt(teams.length);
			return teams[randIdx];
		}
	}
	
	public static boolean successfulOrNot(Fraction fraction){
		Random random = ThreadLocalRandom.current();

		int chance = random.nextInt(fraction.getPenyebut());
		if (chance < fraction.getPembilang()){
			return true;
		}
		else{
			return false;
		}
	}

	public static Player getRandomPlayer(List<Player> listOfPlayers) {
		Random random = ThreadLocalRandom.current();

		if(listOfPlayers.isEmpty()){
			return null;
		}
		else{
			int randIdx = random.nextInt(listOfPlayers.size());
			return listOfPlayers.get(randIdx);
		}
	}

	public static Action getRandomActions(Action[] actions, int[] probabilities) {
		Random random = ThreadLocalRandom.current();

		List<Action> actionList = new ArrayList<>();

		for(int i=0 ; i < actions.length; i++) {
			Action action = actions[i];
			int probability = probabilities[i];

			actionList.addAll(Collections.nCopies(probability, action));
		}

		int randIdx = random.nextInt(actionList.size());
		return actionList.get(randIdx);
	}
}
