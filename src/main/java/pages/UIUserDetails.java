package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import common.GlobalData;


public class UIUserDetails {
	WebDriver driver;
	static final Logger logger = LogManager.getLogger(UIUserDetails.class.getName());
	
	@FindBy(id = "users")
	WebElement usersTable;

	@FindBy(xpath = "//a[text()='New User']")

	WebElement newUserButton;

	@FindBy(css = "table#users tbody tr:last-child td:nth-child(1)")
	WebElement newlyAddedUserName;

	@FindBy(css = "table#users tbody tr:last-child td:nth-child(2)")
	WebElement newlyAddedUserEmailID;

	@FindBy(css = "table#users tbody tr:last-child td:nth-child(3)")
	WebElement newlyAddedUserPassword;

	public UIUserDetails(WebDriver driver) {
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, GlobalData.ElementTimeout);
		PageFactory.initElements(factory, this);
	}

	/**
	 * This method will get the username from the list of usernames
	 * @return
	 */
	public String getNewUserName() {
		String newUserName = "";
		try {
			newUserName = newlyAddedUserName.getText();
			logger.info("Fetching new user name from all users table : " + newUserName);
			return newUserName;
		} catch (Exception e) {
			logger.error("Error in fetching user name from all users table");
			e.printStackTrace();
			return newUserName;
		}
	}

	/**
	 * This method will get the Email ID from the list of Email IDs
	 * @return
	 */
	public String getNewUserEmailID() {
		String newUserEmailID = "";
		try {
			newUserEmailID = newlyAddedUserEmailID.getText();
			logger.info("Fetching new user email id from all users table : " + newUserEmailID);
			return newUserEmailID;
		} catch (Exception e) {
			logger.error("Error in fetching user email id from all users table");
			e.printStackTrace();
			return newUserEmailID;
		}
	}

	/**
	 * This method will get the password from the list of passwords
	 * @return
	 */
	public String getNewUserPassword() {
		String newUserPassword = "";
		try {
			newUserPassword = newlyAddedUserPassword.getText();
			logger.info("Fetching new user password from all users table : " + newUserPassword);
			return newUserPassword;
		} catch (Exception e) {
			logger.error("Error in fetching user password from all users table");
			e.printStackTrace();
			return newUserPassword;
		}
	}

	
	/**
	 * This method will verify if the create new user button is displayed
	 * @return 
	 */
	public boolean verifyNewUserButtonDisplayed() {
		boolean newUserButtonDisplayed = false;
		try {
			newUserButtonDisplayed = newUserButton.isDisplayed();
			logger.info("Verifying new user button displayed on All Users Page : " + newUserButtonDisplayed);
			return newUserButtonDisplayed;
		} catch (Exception e) {
			logger.error("Error while checking new user button on all users page ");
			// e.printStackTrace();
			return newUserButtonDisplayed;
		}
	}

	
	
	/**
	 * This method will click on the new User button
	 */
	public void clickNewUserButton() {
		try {
			newUserButton.click();
			logger.info("Clicking New User Button");
		} catch (Exception e) {
			logger.error("Error while clicking new user button");
			e.printStackTrace();
		}
	}

}
