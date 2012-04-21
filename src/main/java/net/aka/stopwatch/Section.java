package net.aka.stopwatch;


public class Section {

	private String taskName;
	private long startTime;
	
	public Section(String taskName) {
		this.taskName = taskName;
		this.startTime = System.currentTimeMillis();
	}
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
}
