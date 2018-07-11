package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import common.GlobalData;

public class UINewUser {
    WebDriver driver;
	static final Logger logger = LogManager.getLogger(UINewUser.class.getName());
    
    @FindBy(id = "name")
    WebElement name;

    @FindBy(id = "user.name.error")
    WebElement userNameError;

    @FindBy(id = "email")
    WebElement emailID;

    @FindBy(id = "user.email.error")
    WebElement emailError;

    @FindBy(id = "password")
    WebElement passWord;

    @FindBy(id = "user.password.error")
    WebElement passwordError;

    @FindBy(id = "confirmationPassword")
    WebElement confirmPassword;

    @FindBy(id = "user.confirmationPassword.error")
    WebElement confirmPasswordError;

    @FindBy(xpath = "//a[contains(@class,'btn btn-primary') and text()='All User']")
    WebElement allUsersButton;

    @FindBy(xpath = "//button[text()='Submit']")
    WebElement submitButton;


    /**
     * This method will initialize the page instance
     * @param driver - Web Driver instance
     */
    public UINewUser(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,GlobalData.ElementTimeout);
        PageFactory.initElements(factory, this);
    }

    /**
     * This method will enter the username in the username field
     * @param username
     */
    public void setName(String username) {
        try {
            name.sendKeys(username);
            logger.info("User Name entered in User Name field Successfully : " + username);
        } catch (Exception e) {
            logger.error("Unable to enter user name in name field");
            e.printStackTrace();
        }
    }

    /**
     * This method will enter the emailid in the emailid field
     * @param userEmailID
     */
    public void setEmailID(String userEmailID) {
        try {
            emailID.sendKeys(userEmailID);
            logger.info("User Email ID entered in email id field successfully : " + userEmailID);
        } catch (Exception e) {
            logger.error("Unable to enter enter user email id");
            e.printStackTrace();
        }
    }

    /**
     * This method will enter the password in the password field
     * @param userPassword
     */
    public void setPassword(String userPassword) {
        try {
            passWord.sendKeys(userPassword);
            logger.info("User Password entered in password field successfully : " + userPassword);
        } catch (Exception e) {
            logger.error("Unable to enter user password");
            e.printStackTrace();
        }
    }

    /**
     * This method will enter the password in the  confirm password field
     * @param userConfirmPassword
     */
    public void setConfirmPassword(String userConfirmPassword) {
        try {
            confirmPassword.sendKeys(userConfirmPassword);
            logger.info("User Confirm password entered in confirm password field successfully : " + userConfirmPassword);
        } catch (Exception e) {
            logger.error("Unable to enter user confirm password");
            e.printStackTrace();
        }
    }

    /**
     * This method will click the submit button
     */
    public void clickSubmitButton() {
        try {
            submitButton.click();
            logger.info("Submit Button successfully clicked ");
        } catch (Exception e) {
            logger.error("Unable to click on submit button");
            e.printStackTrace();
        }
    }

    /**
     * This methood will click on All User button
     */
    public void clickAllUsersButton() {
        try {
            allUsersButton.click();
            logger.info("All Users Button successfully clicked ");
        } catch (Exception e) {
            logger.error("Unable to click all users button");
            e.printStackTrace();
        }
    }

    
    /**
     * This method will verify whether all users button is displayed
     * @return
     */
    public boolean verifyAllUsersButtonDisplayed() {
        boolean allUsersButtonIsVisible = false;
        try {
            allUsersButtonIsVisible = allUsersButton.isDisplayed();
            logger.info("Verifying All Users Button Display on Page " + allUsersButtonIsVisible);
            return allUsersButtonIsVisible;
        } catch (Exception e) {
            logger.error("Error in checking the visibility of all users button on New User page");
            e.printStackTrace();
            return allUsersButtonIsVisible;
        }
    }

    /**
     * This method will get the username error message display in case of any error
     * @return
     */
    public String getUserNameErrorMessage() {
        String userNameErrorMessage = "";
        try {
            userNameErrorMessage = userNameError.getText();
            return userNameErrorMessage;
        } catch (Exception e) {
            logger.error("Unable to get user name error message");
            e.printStackTrace();
            return userNameErrorMessage;
        }
    }

    /**
     * This method will get the email error message display in case of any error
     * @return
     */
    public String getUserEmailErrorMessage() {
        String userEmailIDErrorMessage = "";
        try {
            userEmailIDErrorMessage = emailError.getText();
            return userEmailIDErrorMessage;
        } catch (Exception e) {
            logger.error("Unable to get user email id error message");
            e.printStackTrace();
            return userEmailIDErrorMessage;
        }
    }

    /**
     * This method will get the password error message display in case of any error
     * @return
     */
    public String getUserPasswordMessage() {
        String userPasswordMessage = "";
        try {
            userPasswordMessage = passwordError.getText();
            return userPasswordMessage;
        } catch (Exception e) {
            logger.error("Unable to get user password error message");
            e.printStackTrace();
            return userPasswordMessage;
        }
    }

    /**
     * This method will get the password error message display in case of any error
     * @return
     */
    public String getUserConfirmPasswordMessage() {
        String userConfirmPasswordMessage = "";
        try {
            userConfirmPasswordMessage = confirmPasswordError.getText();
            return userConfirmPasswordMessage;
        } catch (Exception e) {
            logger.error("Unable to get user confirm password error message");
            e.printStackTrace();
            return userConfirmPasswordMessage;
        }
    }

    /**
     * This method will get the placeholder for the username field
     * @return
     */
    public String getUserNameInputFieldDisplayedText() {
        String userNamePlaceHolderText = "";
        try {
            userNamePlaceHolderText = name.getAttribute("placeholder");
            logger.info("User Name Input Field Text : " + userNamePlaceHolderText);
            return userNamePlaceHolderText;
        } catch (Exception e) {
            logger.error("Error in fetching User Name Input Field Text");
            e.printStackTrace();
            return userNamePlaceHolderText;
        }
    }

    /**
     * This method will get the placeholder for the emailid field
     * @return
     */
    public String getUserEMailIDInputFieldDisplayedText() {
        String userEmailIDPlaceHolderText = "";
        try {
            userEmailIDPlaceHolderText = emailID.getAttribute("placeholder");
            logger.info("User EmailID Input Field Text : " + userEmailIDPlaceHolderText);
            return userEmailIDPlaceHolderText;
        } catch (Exception e) {
            logger.error("Error in fetching User EmailID Input Field Text");
            e.printStackTrace();
            return userEmailIDPlaceHolderText;
        }
    }

    /**
     * This method will get the placeholder for the password field
     * @return
     */
    public String getUserPasswordInputFieldDisplayedText() {
        String userPasswordPlaceHolderText = "";
        try {
            userPasswordPlaceHolderText = passWord.getAttribute("placeholder");
            logger.info("User Password Input Field Text : " + userPasswordPlaceHolderText);
            return userPasswordPlaceHolderText;
        } catch (Exception e) {
            logger.error("Error in fetching User Password Input Field Text");
            e.printStackTrace();
            return userPasswordPlaceHolderText;
        }
    }

    /**
     * This method will get the placeholder for the confirm password field
     * @return
     */
    public String getUserConfirmPasswordInputFieldText() {
        String userConfirmPasswordPlaceHolderText = "";
        try {
            userConfirmPasswordPlaceHolderText = confirmPassword.getAttribute("placeholder");
            logger.info("User Confirm Password Input Field Text : " + userConfirmPasswordPlaceHolderText);
            return userConfirmPasswordPlaceHolderText;
        } catch (Exception e) {
            logger.error("Error in fetching User Confirm Password Input Field Text");
            e.printStackTrace();
            return userConfirmPasswordPlaceHolderText;
        }
    }

    
    /**
     * This method will enter the user details in the Submit new user page
     * @param userName
     * @param userEmail
     * @param password
     * @param confirmPassword
     */
    public void generateNewUser(String userName, String userEmail, String password, String confirmPassword) {
        try {
            setName(userName);
            setEmailID(userEmail);
            setPassword(password);
            setConfirmPassword(confirmPassword);
            clickSubmitButton();
            logger.info("Successfully Submitted New user Details ");
        } catch (Exception e) {
            logger.error("Exception while generating new User");
            e.printStackTrace();
        }
    }

}
