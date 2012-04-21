package net.aka.stopwatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class StopWatchTest {

	class TestEventLogger extends EventLogger {
		private String id;
		private Section section;
		private String info;
		
		@Override
		public void onStop(String id, Section section, String info) {
			super.onStop(id, section, info);
			this.id = id;
			this.section = section;
			this.info = info;
		}

		public String getId() {
			return id;
		}
		public Section getSection() {
			return section;
		}
		public String getInfo() {
			return info;
		}
	}
	
	@Test
	public void スタートストップのみ() {
		TestEventLogger logger = new TestEventLogger();
		StopWatch s = new StopWatch("testA", logger);
		s.start("task1");
		s.stop("");
		assertEquals("testA", logger.getId());
		assertEquals("task1", logger.getSection().getTaskName());
	}
	
	@Test
	public void スタートなしでストップされた場合は何もしない() {
		TestEventLogger logger = new TestEventLogger();
		StopWatch s = new StopWatch("testB", logger);
		s.stop("");
	}
	
	@Test
	public void startが重複して実行された場合は前のtaskを無視() {
		TestEventLogger logger = new TestEventLogger();
		StopWatch s = new StopWatch("testC", logger);
		s.start("task1");
		s.start("task2");
		s.stop("");
		assertEquals("testC", logger.getId());
		assertEquals("task2", logger.getSection().getTaskName());
	}
	
	@Test
	public void split実行時は前のタスクを終了してから開始() {
		TestEventLogger logger = new TestEventLogger();
		StopWatch s = new StopWatch("testD", logger);
		s.start("task1");
		s.split("task2");
		s.stop("");
		assertEquals("testD", logger.getId());
		assertEquals("task2", logger.getSection().getTaskName());
	}
	
	@Test
	public void ランダムIDでStopWatchインスタンス生成() {
		StopWatch watch1 = new StopWatch();
		StopWatch watch2 = new StopWatch();
		assertFalse(watch1.getId().equals(watch2.getId()));
	}

	@Test
	public void 停止時に追加情報を出力() {
		TestEventLogger logger = new TestEventLogger();
		StopWatch s = new StopWatch("testE", logger);
		s.start("task1");
		s.stop("STOP");
		assertEquals("testE", logger.getId());
		assertEquals("task1", logger.getSection().getTaskName());
		assertEquals("STOP", logger.getInfo());
	}
	
	@Test
	public void 計測処理を無名クラスで渡す() {
		TestEventLogger logger = new TestEventLogger();
		StopWatch s = new StopWatch("testF", logger);
		Object result = s.doWith("task1", new WrappedProcess() {
			@Override
			public Object execute() {
				return "PROCESS";
			}
		});
		assertEquals("PROCESS", result);
	}
	
}
