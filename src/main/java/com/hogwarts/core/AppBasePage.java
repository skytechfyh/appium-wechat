package com.hogwarts.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author fyh
 * @date 2020/11/19
 * @desc 所有page的父类
 */
public class AppBasePage {

	public AppiumDriver<MobileElement> driver;

	public WebDriverWait wait;

	private TouchAction touchAction;

	public AppBasePage() {

	}

	public AppBasePage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		AppiumFieldDecorator decorator = new AppiumFieldDecorator(this.driver, Duration.ofMillis(5000));
		PageFactory.initElements(decorator, this);
	}


	public TouchAction getTouchAction(){
		return new TouchAction(driver);
	}

	public WebDriverWait getWait(){
		return new WebDriverWait(driver, 60, 1000);
	}

	/**
	 * 点击
	 * @param element	元素
	 */
	public void click(WebElement element){
		click(element, false);
	}

	public void click(WebElement element, boolean scroll){
		if (scroll){
			scroll(element);
		}
		try {
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向下滑动找元素
	 * @param element	元素
	 */
	public void scroll(WebElement element){
		implicitlyWait(1, TimeUnit.SECONDS);
		int startX = driver.manage().window().getSize().getWidth() / 2;
		int startY = driver.manage().window().getSize().getHeight() / 2;

		long startTime = System.currentTimeMillis();

		while (true){
			if (!element.isDisplayed()){
				getTouchAction().moveTo(PointOption.point(startX, startY - 50)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)));
				long endTime = System.currentTimeMillis();
				if ((endTime - startTime) > 60*1000){
					break;
				}
			}else {
				break;
			}
		}
		implicitlyWait(5, TimeUnit.SECONDS);
	}

	private void implicitlyWait(long time, TimeUnit unit) {
		driver.manage().timeouts().implicitlyWait(time, unit);
	}

	/**
	 * 输入信息
	 * @param element	元素
	 * @param text	内容
	 */
	public void inputText(WebElement element, String text){
		if (element.isDisplayed()){
			element.clear();
			element.sendKeys(text);
		}else {
			getWait().until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
		}
	}
}

