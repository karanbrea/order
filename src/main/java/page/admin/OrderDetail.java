package page.admin;

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
	 * 订单编号
	 */
	@FindBy(xpath = "//span[contains(text(),'订单编号：')]")
	WebElement order_ID;

	/**
	 * 订单状态
	 */
	@FindBy(xpath = "//div[contains(text(),'状态：')]/span")
	WebElement order_status;

	/**
	 * 订单总价格
	 */
	@FindBy(xpath = "//div[contains(text(),'订单金额：')]")
	WebElement order_price;

	/**
	 * 审核通过
	 */
	@FindBy(xpath = "//span[contains(text(),'通过')]")
	WebElement confirmed_passed;

	/**
	 * 审核拒绝
	 */
	@FindBy(xpath = "//span[contains(text(),'拒绝')]")
	WebElement confirmed_refused;

	/**
	 * 拼接订单总价格
	 * 
	 * @return 含2位小数点的订单总价格
	 */
	public double order_price() {
		while (true) {
			String[] price = order_price.getText().split("¥ ")[1].split(",");
			for (int i = 0; i < price.length - 1; i++) {
				price[0] = price[0].concat(price[i + 1]);
			}
			price = price[0].split("[.]");
			price[0] = price[0].concat(price[1]);
			return Double.valueOf(price[0]) / 100;
		}
	}

	/**
	 * 审核通过
	 */
	public String confirmed_passed() {
		action_click(confirmed_passed);
		return is_success();
	}
	
	/**
	 * 审核拒绝
	 */
	public String confirmed_refused() {
		action_click(confirmed_refused);
		return is_success();
	}

}
