package test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 测试数据类
 * 
 * @author Administrator
 *
 */
public class TestData extends TestSource {
	public TestData(String fileName) {
		init(fileName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see unicom_test.im.order.data.TestSource#init(java.lang.String)
	 */
	@Override
	public void init(String fileName) {
		// TODO Auto-generated method stub
		String file = "./data/" + fileName + ".properties";
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(file));
			this.property.load(is);
			is.close();
			TestLogs.log.info("获取测试数据:" + file + " 成功!");
		} catch (IOException e) {
			TestLogs.log.error("获取测试数据:" + file + " 失败!", e);
		}
	}

	public static void up_load_file(String fileName) {
		// 指定图片的路径，使用相对路径
		StringSelection stringSelection = new StringSelection(fileName);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		// 把图片路径复制粘贴到剪切板

		Robot robot;
		try {
			Thread.sleep(1000);
			robot = new Robot();
			// 按下Enter键
			robot.keyPress(KeyEvent.VK_ENTER);
			// 释放Enter键
			robot.keyRelease(KeyEvent.VK_ENTER);

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);

			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
		} catch (AWTException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public Properties testData = new Properties();

	/**
	 * 装载测试数据文件数据
	 * 
	 * @param testDataFile ./conf/文件夹下对应的测试数据文件名
	 */
//	private void initTestData(String testDataFile) {
//		String file = "./data/" + testDataFile + ".properties";
//		try {
//			InputStream is = new BufferedInputStream(new FileInputStream(file));
//			testData.load(is);
//			is.close();
//			TestLogs.log.info("获取测试数据:" + file + " 成功!");
//		} catch (IOException e) {
//			TestLogs.log.error("获取测试数据:" + file + " 失败!", e);
//		}
//		 计数当前测试文件中有多少个设备需要测试，并计入deviceCount中
//		 设置参数设备个数上限为1000
//		for (int num = 0; num < 999; num++) {
//			String deviceType = null;
//			try {
//				deviceType = testData.getProperty(num + "_deviceModel");
//				if (deviceType == null) {
//					TestLogs.log.error("testData中不存在key=" + num + "_deviceModel" + "的值", e);
//					testData.setProperty("deviceCount", String.valueOf(num - 1));
//					TestLogs.log.info("testData.setProperty(\"deviceCount\", String.valueOf(" + num + ")=" + deviceType);
//					break;
//				}
//			} catch (Exception e) {
//				TestLogs.log.error("testData中不存在key=" + num + "_deviceModel" + "的值", e);
//				testData.setProperty("deviceCount", String.valueOf(num));
//				TestLogs.log.debug("testData.setProperty(\"deviceCount\", String.valueOf(" + num + ")=" + deviceType);
//				break;
//			}
//			if (deviceType == null) {
//			testData.setProperty("deviceCount", String.valueOf(num));
//			TestLogs.log.debug("testData.setProperty(\"deviceCount\", String.valueOf(" + num + ")=" + deviceType);
//			break;
//			}
//		}
//	}

//	/**
//	 * 获取指定测试数据
//	 * 
//	 * @param key key名
//	 * @return key值或null
//	 */
//	public static String getTestData(String key) {
//		String value = null;
//		if (testData.size() == 0) {
//			TestLogs.log.error("测试数据为空");
//		}
//		try {
//			value = new String(testData.getProperty(key).getBytes("ISO-8859-1"), "utf-8");
//			if (value.length() != 0) {
//				return value;
//			} else {
//				TestLogs.log.error("testData中不存在key=" + key + "的值");
//			}
//		} catch (UnsupportedEncodingException e) {
//			TestLogs.log.error("", e);
//		}
//		return value;
//	}
//
//	/**
//	 * 往测试数据中添加指定键值
//	 * 
//	 * @param key   键名
//	 * @param value 键值
//	 * @return true or false
//	 */
//	public static boolean setTestData(String key, String value) {
//		if (testData != null) {
//			String str = null;
//			try {
//				str = new String(value.getBytes("ISO-8859-1"), "utf-8");
//				testData.setProperty(key, str);
//			} catch (UnsupportedEncodingException e) {
//				TestLogs.log.error("testData写入key=" + key + "=" + str + "失败");
//				return false;
//			}
//			return true;
//		} else {
//			TestLogs.log.error("测试数据为空");
//			return false;
//		}
//	}

}
