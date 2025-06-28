package nse.utils;

import org.apache.logging.log4j.*;

public class LoggerUtil {
	private static final Logger log = LogManager.getLogger(LoggerUtil.class);
	public static void info(String msg){ log.info(msg); }
	public static void error(String msg){ log.error(msg); }
}