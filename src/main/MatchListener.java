package main;

public interface MatchListener {
	void onEvent(Event event);
	void onAction(Action action);
	void onTick(int minutes, int seconds);
}
