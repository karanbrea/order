package page.admin;

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
	 * 导航菜单——订单
	 */
	@FindBy(xpath = "//a[contains(text(),'订单')]")
	WebElement order_center;

	/**
	 * 待确认订单数 TBC=to bo confirmed
	 */
	@FindBy(xpath = "//div[contains(text(),'待确认订单')]/following-sibling::div")
	WebElement order_count_TBC;

	/**
	 * 查看按钮
	 */
	@FindBy(css = "a[href*='#/OrderDetail?orderId=']")
	List<WebElement> order_list_detail;

	/**
	 * 查看订单详情页面
	 * 
	 * @param index 订单列表中订单的排位序号，从0开始
	 * @return 成功返回null，失败返回页面报错信息
	 */
	public String order_detial(Integer index) {
		WebElement order_detail = null;
		if ((Integer) index != null) {
			order_detail = this.order_list_detail.get(index);
		} else {
			order_detail = this.order_list_detail.get(0);
		}
		action_click(order_detail);
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
	 * @return 返回当前用户下待确认的订单总数
	 */
	public int order_count_TBC() {
		return Integer.valueOf(this.order_count_TBC.getText());
	}

}
