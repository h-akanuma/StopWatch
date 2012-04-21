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
	public void �X�^�[�g�X�g�b�v�̂�() {
		TestEventLogger logger = new TestEventLogger();
		StopWatch s = new StopWatch("testA", logger);
		s.start("task1");
		s.stop("");
		assertEquals("testA", logger.getId());
		assertEquals("task1", logger.getSection().getTaskName());
	}
	
	@Test
	public void �X�^�[�g�Ȃ��ŃX�g�b�v���ꂽ�ꍇ�͉������Ȃ�() {
		TestEventLogger logger = new TestEventLogger();
		StopWatch s = new StopWatch("testB", logger);
		s.stop("");
	}
	
	@Test
	public void start���d�����Ď��s���ꂽ�ꍇ�͑O��task�𖳎�() {
		TestEventLogger logger = new TestEventLogger();
		StopWatch s = new StopWatch("testC", logger);
		s.start("task1");
		s.start("task2");
		s.stop("");
		assertEquals("testC", logger.getId());
		assertEquals("task2", logger.getSection().getTaskName());
	}
	
	@Test
	public void split���s���͑O�̃^�X�N���I�����Ă���J�n() {
		TestEventLogger logger = new TestEventLogger();
		StopWatch s = new StopWatch("testD", logger);
		s.start("task1");
		s.split("task2");
		s.stop("");
		assertEquals("testD", logger.getId());
		assertEquals("task2", logger.getSection().getTaskName());
	}
	
	@Test
	public void �����_��ID��StopWatch�C���X�^���X����() {
		StopWatch watch1 = new StopWatch();
		StopWatch watch2 = new StopWatch();
		assertFalse(watch1.getId().equals(watch2.getId()));
	}

	@Test
	public void ��~���ɒǉ������o��() {
		TestEventLogger logger = new TestEventLogger();
		StopWatch s = new StopWatch("testE", logger);
		s.start("task1");
		s.stop("STOP");
		assertEquals("testE", logger.getId());
		assertEquals("task1", logger.getSection().getTaskName());
		assertEquals("STOP", logger.getInfo());
	}
	
	@Test
	public void �v�������𖳖��N���X�œn��() {
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
