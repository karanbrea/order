package page.client;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import page.Page;

public class OrderDetail extends Page {

	public OrderDetail(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 关闭订单按钮
	 */
	@FindBy(xpath = "//*[text()='关闭订单']")
	WebElement close_order;

	/**
	 * 订单编号
	 */
	@FindBy(xpath = "//div[starts-with(text(),'订单编号')]")
	WebElement order_No;

	/**
	 * 订单日期
	 */
	@FindBy(xpath = "//div[starts-with(text(),'订单日期')]")
	WebElement order_create_date;

	/**
	 * 订单金额
	 */
	@FindBy(xpath = "//div[starts-with(text(),'订单金额')]")
	WebElement order_price;

	/**
	 * 订单状态
	 */
	@FindBy(xpath = "//div[@class='dbc-status']")
	WebElement order_status;

	/**
	 *获取订单总价格（含两位小数点）
	 * @return
	 */
	public double order_price() {
		while (true) {
			String[] price = order_price.getText().split("：")[1].split(",");
			for (int i = 0; i < price.length - 1; i++) {
				price[0] = price[0].concat(price[i + 1]);
			}
			price = price[0].split("[.]");
			price[0] = price[0].concat(price[1]);
			return Double.valueOf(price[0]) / 100;
		}
	}

	/**
	 * 关闭订单
	 */
	public String close_order() {
		action_click(close_order);
		return is_success();
	}
	
}
