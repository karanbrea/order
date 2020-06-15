package cases;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import page.Login;
import test.GetResult;
import test.StartTest;
import test.TestData;

public class Test_Admin implements GetResult {
	SoftAssert assertion = null;
	WebDriver driver = null;
	TestData data = null;
	double price = 0;
	int count_befor = 0, count_after = 0;

	@Test
	@Parameters({ "fileName" })
	public void test_admin_login(String fileName) {
		data = new TestData(fileName);
		driver.get(data.property.getProperty("url"));
		Login login = new Login(driver);
		String msg = login.login(data.property.getProperty("user"), data.property.getProperty("pwd"));
		assertTrue("test_admin_login is failed !" + msg, this.result(msg));
	}

	@Test(dependsOnMethods = "test_admin_login")
	public void test_confirm_passed() {
		String msg = null;
		page.admin.Index index = new page.admin.Index(driver);
		msg = index.order_detial(null);
		assertTrue("open order detail is failed !" + msg, this.result(msg));
		
		page.admin.OrderDetail order = new page.admin.OrderDetail(driver);
		price = order.order_price();// 预留数据—获取当前新增订单的总金额
		msg = order.confirmed_passed();
		assertTrue("test_confirm_passed is failed !" + msg, this.result(msg));
	}

	@BeforeClass
	public void beforeClass() {
		driver = new StartTest().init();
		driver.manage().window().maximize();
		assertion = new SoftAssert();
	}

	@AfterClass
	public void afterClass() {
		driver.close();
		driver.quit();
	}

	/**
	 * 判断结果是否失败：非空=失败
	 */
	@Override
	public boolean result(String str) {
		// TODO Auto-generated method stub
		return str == null;
	}

	/**
	 * 判断结构是否成功，0=失败
	 */
	@Override
	public boolean result(int n) {
		// TODO Auto-generated method stub
		return n != 0;
	}

}
