package test;

import org.apache.log4j.Logger;

/**
 * 尝试Log4j中Logger的用法
 * 
 * @author Administrator
 *
 */
public final class TestLogs {

	public static final Logger log = Logger.getLogger(TestLogs.class);

	public static final Logger log(@SuppressWarnings("rawtypes") Class clazz) {
		return Logger.getLogger(clazz);
	}

}