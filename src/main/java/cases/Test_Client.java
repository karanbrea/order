package cases;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import page.Login;
import page.client.CreateOrder;
import page.client.Index;
import page.client.OrderDetail;
import test.GetResult;
import test.StartTest;
import test.TestData;

public class Test_Client implements GetResult {
	SoftAssert assertion = null;
	WebDriver driver = null;
	TestData data = null;
	double price = 0;
	int count_befor = 0, count_after = 0;

	@Test
	@Parameters({ "fileName" })
	public void test_login(String fileName) {
		data = new TestData(fileName);
		driver.get(data.property.getProperty("url"));
		Login login = new Login(driver);
		String msg = login.login(data.property.getProperty("user"), data.property.getProperty("pwd"));
		assertTrue("test_login is failed !" + msg, this.result(msg));
	}

	@Test
	public void test_add_order() {
		String msg = null;
		Index index = new Index(driver);
		CreateOrder order = new CreateOrder(driver);

		count_befor = index.order_count_all();// 预留数据—获取新增订单前总订单数

		msg = index.create_order();
		assertTrue("test_login is failed !" + msg, this.result(msg));

		order.complete_baseinfo();
		order.complete_custominfo();
		price = order.complete_goods_info();// 预留数据—获取当前新增订单的总金额
		msg = order.submit_order();
		assertTrue("test_login is failed !" + msg, this.result(msg));
	}

	@Test(dependsOnMethods = { "test_add_order" })
	public void test_count_check() {
		String msg = null;
		Index index = new Index(driver);
		count_after = index.order_count_all();
		msg = "count_after=>" + count_after + "  count_befor" + count_befor;
		assertTrue("test_count_check is failed !" + msg, count_after > count_befor);
	}

	@Test(dependsOnMethods = { "test_add_order" })
	public void test_price_check() {
		String msg = null;
		Index index = new Index(driver);
		OrderDetail oDetail = new OrderDetail(driver);
		msg = index.order_detial(0);
		assertTrue("test_price_check is failed !" + msg, price == oDetail.order_price());
	}

	@Test(dependsOnMethods = { "test_add_order" })
//	@Test(dependsOnMethods = { "test_login" })
	public void test_order_detail() {
		String msg = null;
		Index index = new Index(driver);
		index.order_center();
		msg = index.order_detial(0);
		assertTrue("test_price_check is failed !" + msg, this.result(msg));
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
