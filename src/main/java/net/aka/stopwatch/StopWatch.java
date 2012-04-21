package net.aka.stopwatch;

import java.util.UUID;

public class StopWatch {

	private static final StopWatchEventListener defaultListener = new EventLogger();
	
	private Section currentSection;
	private String id;
	private StopWatchEventListener listener;
	
	public StopWatch() {
		this(UUID.randomUUID().toString(), defaultListener);
	}
	public StopWatch(String id) {
		this(id, defaultListener);
	}
	public StopWatch(String id, StopWatchEventListener listener) {
		this.id = id;
		this.listener = listener;
	}
	
	public void start(String taskName) {
		this.currentSection = new Section(taskName);
	}
	
	public void stop(String info) {
		this.listener.onStop(this.id, this.currentSection, info);
	}
	
	public void split(String taskName) {
		stop("");
		start(taskName);
	}
	
	public Object doWith(String taskName, WrappedProcess process) {
		start(taskName);
		try {
			return process.execute();
		} finally {
			stop("");
		}
	}
	
	public Section getCurrentSection() {
		return currentSection;
	}
	public String getId() {
		return id;
	}
	public StopWatchEventListener getListener() {
		return listener;
	}

}
