package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.TestNG;

public class StartTest extends Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> suites = new ArrayList<String>();
		suites.add(System.getProperty("user.dir") + "/NewTest.xml");
		System.out.println(suites);
		TestNG tng = new TestNG();
		tng.setTestSuites(suites);
		tng.run();
	}

	@Override
	public WebDriver init() {
		String osName = System.getProperties().getProperty("os.name"); // 获取操作系统名称
		String driverName = null;
		if (osName.contains("Windows")) {
			driverName = "/lib/chromedriver_win32.exe";
		} else if (osName.contains("Mac OS")) {
			driverName = "/lib/chromedriver_mac64";
		} else {
			driverName = "/lib/chromedriver_linux64";
		}
		System.setProperty("webdriver.chrome.driver", new File("").getAbsolutePath() + driverName);// 指定浏览器驱动及其路径
		TestLogs.log.info("os.name=" + osName);
		TestLogs.log.info("webdriver=" + driverName);
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless");
//		options.addArguments("--disable-gpu");
		return new ChromeDriver(options);
	}

}
