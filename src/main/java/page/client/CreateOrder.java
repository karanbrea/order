package page.client;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import page.Page;
import test.TestLogs;

public class CreateOrder extends Page {

	public CreateOrder(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 营销单元
	 */
	@FindBy(xpath = "//input[@placeholder='营销单元']")
	WebElement marketing_unit;

	/**
	 * 客户经理姓名
	 */
	@FindBy(xpath = "//label[text()='客户经理姓名:']/../following-sibling::*//input")
	WebElement custom_manager;

	/**
	 * 客户经理电话
	 */
	@FindBy(xpath = "//label[text()='客户经理电话:']/../following-sibling::*//input")
	WebElement manager_phone;

	/**
	 * 是否签订合同
	 */
	@FindBy(xpath = "//input[@placeholder='是否签订合同']")
	WebElement is_contracted;

	/**
	 * 签约主体
	 */
	@FindBy(xpath = "//input[@placeholder='选择签约主体']")
	WebElement the_unit;

	/**
	 * 服务模式
	 */
	@FindBy(xpath = "//input[@placeholder='选择服务模式']")
	WebElement service_mode;

	/**
	 * 是否计收 calculation and collection
	 */
	@FindBy(xpath = "//input[@placeholder='是否计收']")
	WebElement is_CAC;

	/**
	 * 添加确认函
	 */
	@FindBy(xpath = "//input[@type='file' and @class='el-upload__input']")
	WebElement add_confirmation_letter;

	/**
	 * 客户名称
	 */
	@FindBy(xpath = "//label[text()='客户名称:']/../following-sibling::*//input")
	WebElement company_name;

	/**
	 * 客户姓名
	 */
	@FindBy(xpath = "//label[text()='客户姓名:']/../following-sibling::*//input")
	WebElement custom_name;

	/**
	 * 客户电话
	 */
	@FindBy(xpath = "//label[text()='客户电话:']/../following-sibling::*//input")
	WebElement custom_phone;

	/**
	 * 省份
	 */
	@FindBy(xpath = "//label[text()='选择省份:']/../following-sibling::*//input")
	WebElement province;

	/**
	 * 单选项——自提
	 */
	@FindBy(xpath = "//span[@class='el-radio__label' and text()='自提']")
	WebElement self_mention;

	/**
	 * 单选项——配送
	 */
	@FindBy(xpath = "//span[@class='el-radio__label' and text()='配送']")
	WebElement delivery;

	/**
	 * 配送地址
	 */
	@FindBy(xpath = "//input[@placeholder='请填写配送地址']")
	WebElement address;

	/**
	 * 安装地址
	 */
	@FindBy(xpath = "//label[text()='安装地址：']/../following-sibling::*//input")
	WebElement install_address;

	/**
	 * 安装地址是否同配送地址
	 */
	@FindBy(xpath = "//span[text()='安装地址同配送地址']")
	WebElement is_same_as_install_address;

	/**
	 * 安装方式
	 */
	@FindBy(xpath = "//label[text()='安装方式：']/../following-sibling::*//input")
	WebElement install_mode;

	/**
	 * 安装日期
	 */
	@FindBy(xpath = "//input[@placeholder='选择日期']")
	WebElement install_date;

	/**
	 * 安装时间段
	 */
	@FindBy(xpath = "//input[@placeholder='选择日期']/../following-sibling::*//input")
	WebElement install_time;

	/*
	 * 添加商品
	 */
	@FindBy(xpath = "//span[text()='添加商品']")
	WebElement add_goods;

	/**
	 * 商品名称（下拉选择项）
	 */
	@FindBy(xpath = "//span[text()='保存']/../../../preceding-sibling::*[3]//input")
	List<WebElement> goods_name;

	/**
	 * 商品单价
	 */
	@FindBy(xpath = "//span[text()='保存']/../../../preceding-sibling::*[2]//input")
	List<WebElement> goods_price;

	/**
	 * 商品数量
	 */
	@FindBy(xpath = "//span[text()='保存']/../../../preceding-sibling::*[1]//input")
	List<WebElement> goods_count;

	/**
	 * 保存商品信息
	 */
	@FindBy(xpath = "//span[text()='保存']")
	List<WebElement> save_goods;

	/**
	 * 编辑商品信息
	 */
	@FindBy(xpath = "//span[text()='编辑']")
	List<WebElement> edit_goods;

	/**
	 * 删除商品信息
	 */
	@FindBy(xpath = "//span[text()='删除']")
	List<WebElement> del_goods;

	/**
	 * 提交订单信息
	 */
	@FindBy(xpath = "//span[text()='提交订单']")
	WebElement submit_order;

	/**
	 * 随机填写基础信息
	 */
	public void complete_baseinfo() {
		String img_path = System.getProperty("user.dir") + "/data/confirmation_letter.png";
		this.action_click(marketing_unit, "营销单元");
		this.action_sendKeys(custom_manager, "客户经理姓名");
		this.action_sendKeys(manager_phone, "13312345678");
		this.action_click(is_contracted, "是否签订合同");
		this.action_click(the_unit, "选择签约主体");
		this.action_click(service_mode, "选择服务模式");
		this.action_click(is_CAC, "是否计收");
		try {
			this.action_sendKeys(add_confirmation_letter, img_path);
			TestLogs.log.info("确认函=>" + img_path);
			Thread.sleep(3000);
//			String msg = this.is_success();
//			if (msg != null) {
//				TestLogs.log.error("上传确认函失败");
//			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			TestLogs.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 随机填写客户信息
	 */
	public void complete_custominfo() {
		String address = "安装地址!@#$%^&*_+！@#￥%……&*——+";
		this.action_sendKeys(company_name, "客户名称");
		this.action_sendKeys(custom_name, "客户姓名");
		this.action_sendKeys(custom_phone, "13312345679");
		this.action_click(province, "选择省份");
		if ((new Random().nextInt(7)) % 2 == 0) {
			this.action_click(self_mention);
			TestLogs.log.info("配送方式=>自提");
			this.action_sendKeys(this.install_address, address);
		} else {
			this.action_click(delivery);
			TestLogs.log.info("配送方式=>配送");
			this.action_sendKeys(this.address, address);
			this.action_click(is_same_as_install_address);
			TestLogs.log.info("安装地址=>同配送地址");
		}
		String install_mode_value = this.action_click(install_mode, "安装方式");
		if (!(install_mode_value.equals("无需安装"))) {
			this.action_click(install_date);
			this.action_click(date_table_today);// 选择安装日期
			this.action_click(install_time, "安装时间段");
		}
	}

	/**
	 * 随机填写商品清单信息
	 * 
	 * @return double 订单总价
	 */
	public double complete_goods_info() {
		int count = new Random().nextInt(3) + 2;
		double total_price = 0;
		for (int i = 1; i <= count; i++) {
			this.action_click(add_goods);
			TestLogs.log.info("添加商品信息...");
			this.action_click(goods_name.get(0), "商品名称");
			int price = new Random().nextInt(5000) + 1;
			int goods_count = new Random().nextInt(5) + 1;
			if (i % 2 == 1) {// 单数商品列表时自定义价格
				this.action_sendKeys(goods_price.get(0), price + "");
			} else {
				price = Integer.valueOf(goods_price.get(0).getAttribute("value"));
			}
			TestLogs.log.info("商品单价" + price);
			this.action_sendKeys(this.goods_count.get(0), goods_count + "");
			this.action_click(this.save_goods.get(0));
			total_price += price * goods_count;
			TestLogs.log.info("商品总价=>" + total_price);
		}
		return total_price;
	}

	/**
	 * 提交订单
	 * 
	 * @return String 页面返回信息，成功返回null，失败返回错误信息
	 */
	public String submit_order() {
		this.action_click(submit_order);
		return is_success();
	}

}
