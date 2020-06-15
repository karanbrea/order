package page;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.TestLogs;

public abstract class Page {
	public Page(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 2);
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
	}

	WebDriver driver = null;
	WebDriverWait wait = null;
	Actions action = null;
	JavascriptExecutor jsExecutor = null;

	/**
	 * alter
	 */
	@FindBy(xpath = "//div[ @class='el-message el-message--error' or @class='el-message el-message--warning']//p")
	WebElement alter;

	/**
	 * 页面必输项报错信息
	 */
	@FindBy(xpath = "//div[ @class='el-form-item__error']")
	List<WebElement> item_error;

	/**
	 * 统一下拉选择项控件
	 */
	@FindBy(xpath = "//div[@class='el-select-dropdown el-popper' and @x-placement]//ul/li")
	List<WebElement> drop_down_list;

	/**
	 * 日期控件——当前日期
	 */
	@FindBy(xpath = "//table[@cellspacing and @cellpadding]//td[@class='available today']")
	protected WebElement date_table_today;

	/**
	 * 判断当前页面是否有异常信息抛出
	 * 
	 * @return 成功返回null，失败返回页面异常信息
	 */
	public String is_success() {
		StringBuffer msg = new StringBuffer();
		try {
//			Thread.sleep(4000);
//			msg.append(wait.until(ExpectedConditions.visibilityOf(alter)).getText());
			msg.append(alter.getText());
			TestLogs.log.error(msg);
			return msg.toString().length() > 1 ? msg.toString() : null;
		} catch (Exception e) {
		}
		try {
			item_error.forEach(error -> {
				TestLogs.log.error(error.getText());
				msg.append(error.getText() + "\n");
			});
			return msg.toString().length() > 1 ? msg.toString() : null;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 对该页面元素进行click操作
	 * 
	 * @param element 当前页面类中以申明的可进行click操作的变量名
	 * @return 被点击的元素value值
	 */
	public String action_click(WebElement element) {
		String value = element.getAttribute("value");
		element.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 
	 * 从下拉框里随机选择一个value进行click操作 对日期控件无效
	 * 
	 * @param drop_down_name 当前下拉项在页面显示中对应的名称
	 * @return 被选择元素的getText()结果
	 */
	public String action_click(String drop_down_name) {
		WebElement element = this.drop_down_list.get(new Random().nextInt(drop_down_list.size()));
		String value = element.getText();
		try {
			action.moveToElement(element);
			Thread.sleep(200);
			element.click();
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 从下拉选项中选择指定value项进行click操作 对日期控件无效
	 * 
	 * @param drop_down_name  页面中显示的下拉选项的名称
	 * @param drop_down_value 页面中显示的下拉选项的值
	 * @return 被选择的value内容
	 */
	public String action_click(String drop_down_name, String drop_down_value) {
		String value = null;
		for (WebElement element : drop_down_list) {
			if (element.getText().equals(drop_down_value)) {
				value = element.getText();
				element.click();
				break;
			}
		}
//		this.drop_down_list.forEach(element -> {
//			if (element.getText().equals(drop_down_value)) {
//				TestLogs.log.info(drop_down_name + "=>" + element.getText());
//				element.click();
//			}
//		});
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 对页面指定元素element进行cilck操作，并选择drop_down_value 对日期控件无效
	 * 
	 * @param element         页面元素 element（页面类中定义的变量名）
	 * @param drop_down_name  页面元素名称（页面中显示的字段名）
	 * @param drop_down_value 需要被选择中的下拉选项的值
	 * @return 被选择的value值
	 */
	public String action_click(WebElement element, String drop_down_name, String drop_down_value) {
		this.action_click(element);
		this.action_click(drop_down_name, drop_down_value);
		String value = element.getAttribute("value");
		TestLogs.log.info(drop_down_name + "=>" + value);
		return value;
	}

	/**
	 * 对页面指定元素element进行cilck操作，并选择随机算则一个下拉值 对日期控件无效
	 * 
	 * @param element        页面元素 element（页面类中定义的变量名）
	 * @param drop_down_name 页面元素名称（页面中显示的字段名）
	 * @return 被选择的value值
	 */
	public String action_click(WebElement element, String drop_down_name) {
		this.action_click(element);
		this.action_click(drop_down_name);
		String value = element.getAttribute("value");
		TestLogs.log.info(drop_down_name + "=>" + value);
		return value;
	}

	/**
	 * 对该页面元素先进行clear操作，再进行sendKeys操作 对日期控件无效
	 * 
	 * @param element 当前页面类中已申明的可进行sendKeys操作的变量名
	 * @param value   将被被填写的内容
	 */
	public void action_sendKeys(WebElement element, String value) {
		try {
			element.clear();
		} catch (Exception e) {
			TestLogs.log.error(e.getMessage());
		}
		try {
			element.sendKeys(value);
		} catch (Exception e) {
			TestLogs.log.error(e.getMessage());
		}
	}
}
