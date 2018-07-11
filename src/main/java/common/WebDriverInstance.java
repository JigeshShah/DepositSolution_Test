package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import io.github.bonigarcia.wdm.*;

public class WebDriverInstance {

	static final Logger logger = LogManager.getLogger(WebDriverInstance.class.getName());
	public  WebDriver driver;
	UtilityCommon utilData= new UtilityCommon();
	
	/**
	 * This method will initialize the driver mentioned in the config file
	 * @return
	 */
	public WebDriver startDriver()
	{
		try
		{
		String browserType=utilData.getProperties("browser");


		if(browserType.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		
		else if(browserType.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		else if(browserType.equalsIgnoreCase("opera"))
		{
			WebDriverManager.operadriver().setup();
			driver= new OperaDriver();
		}
		else if(browserType.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}
		else if(browserType.equalsIgnoreCase("ie"))
		{
			WebDriverManager.iedriver().setup();
			driver= new InternetExplorerDriver();
		}
		else
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		
		logger.info(browserType + "Browser  has been successfully launched");
		return driver;
	}

	catch(Exception e)
	{
	return null;	
	}
	}
}
