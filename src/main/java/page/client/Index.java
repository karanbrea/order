package page.client;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import page.Page;

public class Index extends Page {

	public Index(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 导航菜单——订单中心
	 */
	@FindBy(xpath = "//a[text()='订单中心']")
	WebElement order_center;

	/**
	 * 按钮——创建订单
	 */
//	@FindBy(xpath = "//span[text()='创建订单']/..")
	@FindBy(css = "button.m-q-create")
	WebElement create_order;

	/**
	 * 全部订单个数
	 */
	@FindBy(xpath = "//p[text()='全部订单']/preceding-sibling::p")
	WebElement order_count_all;

	/**
	 * 已关闭订单个数
	 */
	@FindBy(xpath = "//p[text()='已关闭订单']/preceding-sibling::p")
	WebElement order_count_closed;

	/**
	 * 待关闭订单个数
	 */
	@FindBy(xpath = "//p[text()='待关闭订单']/preceding-sibling::p")
	WebElement order_count_closing;

	/**
	 * 已取消订单个数
	 */
	@FindBy(xpath = "//p[text()='已取消订单']/preceding-sibling::p")
	WebElement order_count_canceled;

	/**
	 * 订单列表
	 */
	@FindBy(xpath = "//div[@class='table']//div[@class='t-c-data']")
	List<WebElement> order_list;

	/**
	 * 进入新建订单页面
	 * 
	 * @return 成功返回null，失败返回报错信息
	 */
	public String create_order() {
		this.action_click(create_order);
		return is_success();
	}

	/**
	 * 进入订单详情页面
	 * 
	 * @param index 订单列表中订单的排位序号，从0开始
	 * @return 成功返回null，失败返回页面报错信息
	 */
	public String order_detial(Integer index) {
		if ((Integer) index != null) {
			this.action_click(this.order_list.get(index));
		} else {
			this.order_list.get(0);
		}
		return this.is_success();
	}

	/**
	 * 进入订单中心
	 * 
	 * @return 成功返回null，失败返回报错信息
	 */
	public String order_center() {
		this.action_click(order_center);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		return is_success();
	}

	/**
	 * 
	 * @return 返回当前用户下所有状态的订单总数
	 */
	public int order_count_all() {
		return Integer.valueOf(this.order_count_all.getText());
	}

}
