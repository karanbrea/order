package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import test.TestLogs;

public class Login extends Page{

	public Login(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * user
	 */
	@FindBy(xpath = "//input[@placeholder='请输入手机号']")
	WebElement input_user;

	/**
	 * password
	 */
	@FindBy(xpath = "//input[@placeholder='请输入密码']")
	WebElement input_pwd;

	/*
	 * password seeable
	 */
	@FindBy(xpath = "el-input__icon el-icon-view el-input__clear")
	WebElement see_pwd;

	/*
	 * remember me
	 */
	@FindBy(xpath = "//span[starts-with(@class,'el-checkbox__input')]")
	WebElement rmb_pwd;

	/*
	 * fotget password
	 */
	@FindBy(xpath = "//a[text()='忘记密码']")
	WebElement fpwd;

	/*
	 * login button
	 */
	@FindBy(xpath = "//button/span[text()='登录']")
	WebElement cmt;

	/*
	 * 
	 */
	public String login(String user, String pwd) {
		input_user.clear();
		input_user.sendKeys(user);
		input_pwd.clear();
		input_pwd.sendKeys(pwd);
		this.cmt.click();
		TestLogs.log.info(user+"正在登录");
		return is_success();
	}

	/*
	 * 
	 */
	public boolean rmb_pwd() {
		this.rmb_pwd.click();
		return rmb_pwd.getAttribute("className").contains("is-checked") ? true : false;
	}

	/*
	 * 
	 */
	public void forget_pwd() {
		this.fpwd.click();
	}
}
