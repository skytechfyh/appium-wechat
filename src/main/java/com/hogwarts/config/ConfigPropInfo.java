package com.hogwarts.config;

import java.io.IOException;
import java.util.Properties;

/**
 * @author fyh
 * @date 2020/11/19
 * @desc  配置解析
 */
public class ConfigPropInfo {

	public static String APP_SERVER = null;

	public static String UDID = null;

	public static String DEVICE_NAME = null;

	public static String APP_PACKAGE = null;

	public static String APP_ACTIVITY = null;

	public static String APP_PLATFORM_NAME = null;

	public static Long APP_ADB_EXEC_TIMEOUT = null;

	static {
		Properties pro = new Properties();
		try {
			pro.load(ConfigPropInfo.class.getClassLoader().getResourceAsStream("config.properties"));

			UDID = pro.getProperty("app.udid");

			DEVICE_NAME = pro.getProperty("app.deviceName");

			APP_PACKAGE = pro.getProperty("app.package");

			APP_ACTIVITY = pro.getProperty("app.activity");

			APP_PLATFORM_NAME = pro.getProperty("app.platformName");

			APP_SERVER = pro.getProperty("app.server");

			APP_ADB_EXEC_TIMEOUT = Long.parseLong(pro.getProperty("app.adb.exec.timeout"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
