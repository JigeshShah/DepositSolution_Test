package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import API.UserAPI;
import common.GlobalData;
import common.WebDriverInstance;

public class BaseUserTest {
	static final Logger logger = LogManager.getLogger(BaseUserTest.class.getName());
	static WebDriver driver;

	/**
	 * This method will initialize the driver instance and set the global data that are required during test run
	 * @throws Exception
	 */
	@BeforeClass
	public static void beforeTest() throws Exception {
		GlobalData GData = new GlobalData();
		GData.setGlobalData();
		UserAPI.deleteAllUsers(GlobalData.DeleteUserURL);
		WebDriverInstance WMgr = new WebDriverInstance();
		driver = WMgr.startDriver();

	}

	/**
	 * This method will close the driver instance post test run
	 */
	@AfterClass
	public static void afterTest() {
		driver.quit();

	}

}
