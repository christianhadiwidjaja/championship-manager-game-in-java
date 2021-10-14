package main;

import java.util.Arrays;
import java.util.List;

public class Hold extends AbstractAction {

    public Hold(Player actionPlayer, Player targetPlayer) {
        super(actionPlayer, targetPlayer);
    }


    @Override
    public List<String> getComments() {
        String comment = getActionPlayer().getPlayerName() + " with the ball";
        return Arrays.asList(comment);
    }

    @Override
    public double getActionProbability(Team opposingTeam) {
        Player player = getActionPlayer();

        double probability = 0.8 + player.getPlayerSkillPoint()/20 * 0.05;
        return probability;
    }

    @Override
    public void completeEventFields(Event event, Team opposingTeam) {
        Player newPlayer;
        if (event.isSuccessful()){
            newPlayer = getActionPlayer();
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
            comment = "still with the ball";
        }
        else{
            comment = "the ball is stolen by " + newPlayer.getPlayerName();
        }
        return Arrays.asList(comment);
    }
}
