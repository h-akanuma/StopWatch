package net.aka.stopwatch;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventLogger implements StopWatchEventListener {
	
	private final Logger logger = LoggerFactory.getLogger("stopwatch");
	private static final String LOG_SEPARATOR = "\t";

	@Override
	public void onStop(String id, Section section, String info) {
		if(section == null) {
			return;
		}
		long elapsed = System.currentTimeMillis() - section.getStartTime();
		List<String> logValues = Arrays.asList(new String[]{id, section.getTaskName(), Long.toString(elapsed), info});
		logger.info(StringUtils.join(logValues, LOG_SEPARATOR));
	}

}
