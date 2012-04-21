package net.aka.stopwatch;

public interface StopWatchEventListener {

	void onStop(String id, Section section, String info);
	
}
