package com.hogwarts.core;

import com.hogwarts.config.ConfigPropInfo;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author fyh
 * @date 2020/11/19
 * @desc 生成AppiumDriver的工厂类
 */
public class AppFactory {

	public static AppiumDriver<MobileElement> getDriver() throws MalformedURLException {
		return new AppiumDriver<>(new URL(ConfigPropInfo.APP_SERVER), new AppOptions().initAndroid());
	}

}
