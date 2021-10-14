package ui;

import main.Action;
import main.Event;
import main.MatchListener;

public class Reporter implements MatchListener {

    @Override
    public void onEvent(Event event) {
        for(String comment : event.getComments()) {
            System.out.println(comment);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAction(Action action) {
        for(String comment : action.getComments()) {
            System.out.println(comment);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTick(int minutes, int seconds) {

    }


}
