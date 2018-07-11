package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import API.UserAPI;
import common.GlobalData;
import common.UtilityCommon;
import pages.UINewUser;
import pages.UIUserDetails;

public class TestUserScenarios extends BaseUserTest {

	static final Logger logger = LogManager.getLogger(TestUserScenarios.class.getName());
	static UINewUser newUser;
	static UIUserDetails allUsers;

	UtilityCommon UtilObj = new UtilityCommon();
	UserAPI UAPI = new UserAPI();

	/**
	 * This method will initialize the page objects
	 */
	@BeforeClass
	public static void initializePageObjects() {
		newUser = new UINewUser(driver);
		allUsers = new UIUserDetails(driver);
	}

	/**
	 * This method will navigate to home page before the start of each scenario
	 */
	@Before
	public void NavigateToHome() {
		driver.get(GlobalData.HomePageURL);
	}

	/**
	 * TestCase -01 - Blank UserName - This method will test if we are getting
	 * correct validation error message when the Username is blank
	 */
	@Test
	public void tc01_ValidateBlankUserNameErrorMessage() {
		try {
			String userEmail = UtilObj.generateRandomEmail();
			String password = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);

			newUser.generateNewUser("", userEmail, password, password);
			logger.info("TestCase -01 - Blank UserName");
			Assertions.assertTrue(newUser.getUserNameErrorMessage().equals("Required"));
		} catch (Exception e) {
			logger.error("Error while testing --TestCase -01 - Blank UserName");
			e.printStackTrace();
		}
	}

	/**
	 * TestCase -02 - Blank Email - This method will test if we are getting correct
	 * validation error message when the Email is blank
	 */
	@Test
	public void tc02_ValidateBlankEmailErrorMessage() {
		try {
			String userName = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);
			String password = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);

			newUser.generateNewUser(userName, "", password, password);
			logger.info("TestCase -02 - Blank Email");
			Assertions.assertTrue(newUser.getUserEmailErrorMessage().equals("Required"));
		} catch (Exception e) {
			logger.error("Error while testing -- TestCase - 02 - Blank Email");
			e.printStackTrace();
		}
	}

	/**
	 * TestCase -03 - Blank Password - This method will test if we are getting
	 * correct validation error message when the Password is blank
	 */
	@Test
	public void tc03_ValidateBlankPasswordErrorMessage() {
		try {
			String userName = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);
			String userEmail = UtilObj.generateRandomEmail();
	
			newUser.generateNewUser(userName, userEmail, "", "");
			logger.info("TestCase -03 - Blank Password");
			Assertions.assertTrue(newUser.getUserPasswordMessage().equals("Required"));
		} catch (Exception e) {
			logger.error("Error while testing --TestCase -03 - Blank Password");
			e.printStackTrace();
		}
	}

	/**
	 * TestCase -04 - Validate Error for all blank fields -This method will test if
	 * we are getting correct validation error message when the input fields are
	 * blank
	 */
	@Test
	public void tc04_ValidateAllFieldsErrorMessage() {
		try {
			logger.info("TestCase -04 - Validate Error for all blank fields");
			newUser.generateNewUser("", "", "", "");
			logger.info("User Name Error Message Verification ");
			Assertions.assertTrue(newUser.getUserNameErrorMessage().equals("Required"));
			logger.info("User Email Message Verification");
			Assertions.assertTrue(newUser.getUserEmailErrorMessage().equals("Required"));
			logger.info("User Password Message Verification");
			Assertions.assertTrue(newUser.getUserPasswordMessage().equals("Required"));
		} catch (Exception e) {
			logger.error("Error while testing --TestCase -04 - Validate Error for all blank fields");
			e.printStackTrace();
		}
	}

	/**
	 * Test Case-05 - Valid User Details Test - This method will create a new user
	 * and will validate if user is present in the application through UI and APIs
	 */
	@Test
	public void tc05_CreateValidUser() {
		try {
			logger.info("Test Case-05 - Valid User Details Test ");
			String userName = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);
			String userEmail = UtilObj.generateRandomEmail();
			String password = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);

			newUser.generateNewUser(userName, userEmail, password, password);
			Assertions.assertTrue(allUsers.verifyNewUserButtonDisplayed());
			if (allUsers.getNewUserName().equals(userName)) {
				logger.info("New User Name Matched");
			}
			Assertions.assertTrue(allUsers.getNewUserName().equals(userName));

			if (allUsers.getNewUserEmailID().equals(userEmail)) {
				logger.info("New User Email ID Matched");
			}
			Assertions.assertTrue(allUsers.getNewUserEmailID().equals(userEmail));
			if (allUsers.getNewUserPassword().equals(password)) {
				logger.info("New User password matched");
			}
			Assertions.assertTrue(allUsers.getNewUserPassword().equals(password));
			Assertions.assertTrue(UserAPI.verifyUserDetails(GlobalData.searchUserURL, userName, userEmail, password));

		} catch (Exception e) {
			logger.error("Error while running test - Test Case-05 - Valid User Details Test");
			e.printStackTrace();
		}
	}

	/**
	 * Test Case -06 -- Different Password -This method will validate the error
	 * message when password and confirm password are not same
	 */
	@Test
	public void tc06_DifferentPasswordValidation() {
		try {
			logger.info("Test Case -06 -- Different Password ");

			String userName = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);
			String userEmail = UtilObj.generateRandomEmail();
			String password = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);

			newUser.generateNewUser(userName, userEmail, password, "111111");
			Assertions.assertTrue(newUser.getUserConfirmPasswordMessage().equals("passwords are not the same"));
			Assertions.assertFalse(UserAPI.verifyUserDetails(GlobalData.searchUserURL, userName, userEmail, password));

		} catch (Exception e) {
			logger.error("Error while running test - Test Case - 06 -- Different Password ");
			e.printStackTrace();
		}
	}

	/**
	 * Test Case -07 - Same Password with Different Case -This method will validate
	 * the error message when password and confirm password are same but have
	 * different case
	 */
	@Test
	public void tc07_SamePasswordDifferentCaseValidation() {
		try {
			logger.info("Test Case -07 - Same Password with Different Case");

			String userName = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);
			String userEmail = UtilObj.generateRandomEmail();
			String password = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHA);

			newUser.generateNewUser(userName, userEmail, password.toUpperCase(), password.toLowerCase());
			Assertions.assertTrue(newUser.getUserConfirmPasswordMessage().equals("passwords are not the same"));
			Assertions.assertFalse(UserAPI.verifyUserDetails(GlobalData.searchUserURL, userName, userEmail, password));

		} catch (Exception e) {
			logger.error("Error while running test - Test Case -07 - Same Password with Different Case");
			e.printStackTrace();
		}
	}

	/**
	 * Test Case -08 - Duplicate UserName Test - This method will validate the error
	 * message when we try to enter the duplicate username and email id
	 */
	@Test
	public void tc08_DuplicateUserTest() {
		try {
			logger.info("Test Case -08 - Duplicate UserName Test -");

			String userName = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);
			String userEmail = UtilObj.generateRandomEmail();
			String password = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);

			newUser.generateNewUser(userName, userEmail, password, password);
			allUsers.clickNewUserButton();
			newUser.generateNewUser(userName, userEmail, password, password);

			Assertions.assertTrue(newUser.getUserNameErrorMessage().equals("Must be unique"));
			Assertions.assertTrue(newUser.getUserEmailErrorMessage().equals("Must be unique"));

		} catch (Exception e) {
			logger.error("Error while running testing - Test Case -08 - Duplicate UserName Test");
			e.printStackTrace();
		}
	}

	/**
	 * TestCase - 09 - Long UserName - This method will test if we are getting
	 * correct validation error message when the Username is too long more than 255
	 * characters
	 */
	@Test
	public void tc09_ValidateLongUserNameErrorMessage() {
		try {
			String userName = UtilObj.generateRandomString(256, UtilityCommon.Mode.ALPHANUMERIC);
			String userEmail = UtilObj.generateRandomEmail();
			String password = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);

			newUser.generateNewUser(userName, userEmail, password, password);
			logger.info("TestCase - 09 - Long UserName");
			Assertions.assertTrue(newUser.getUserNameErrorMessage().equals("Maximum size is 255"));
		} catch (Exception e) {
			logger.error("Error while testing - TestCase - 09 - Long UserName");
			e.printStackTrace();
		}
	}

	/**
	 * TestCase -10 - Long Email - This method will test if we are getting correct
	 * validation error message when the Email is too long more than 255 characters
	 */
	@Test
	public void tc10_ValidateLongEmailErrorMessage() {
		try {
			String userName = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);
			String userEmail = UtilObj.generateRandomString(256, UtilityCommon.Mode.ALPHANUMERIC) + "@abc.com";
			String password = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);

			newUser.generateNewUser(userName, userEmail, password, password);
			logger.info("TestCase -10 - Long Email");
			Assertions.assertTrue(newUser.getUserEmailErrorMessage().equals("Maximum size is 255"));
		} catch (Exception e) {
			logger.error("Error while testing - TestCase -10 - Long Email");
			e.printStackTrace();
		}
	}

	/**
	 * TestCase -11 - Long Password - This method will test if we are getting
	 * correct validation error message when the Password is too long more than 255
	 * characters
	 */
	@Test
	public void tc11_ValidateLongPasswordErrorMessage() {
		try {
			String userName = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);
			String userEmail = UtilObj.generateRandomEmail();
			String password = UtilObj.generateRandomString(256, UtilityCommon.Mode.ALPHANUMERIC);

			newUser.generateNewUser(userName, userEmail, password, password);
			logger.info("TestCase -11 - Long Password");
			Assertions.assertTrue(newUser.getUserPasswordMessage().equals("Maximum size is 255"));
		} catch (Exception e) {
			logger.error("Error while testing --TestCase -11 - Long Password");
			e.printStackTrace();
		}
	}

	/**
	 * TestCase -12 - Validate All Fields - This method will validate the fields
	 * displayed on the new user creation page
	 */
	@Test
	public void tc_12_ValidateAllFields() {
		try {
			logger.info("TestCase -12 - Validate All Fields ");
			Assertions.assertTrue(newUser.getUserNameInputFieldDisplayedText().equals("name"));
			Assertions.assertTrue(newUser.getUserEMailIDInputFieldDisplayedText().equals("your@email.com"));
			Assertions.assertTrue(newUser.getUserPasswordInputFieldDisplayedText().equals("min 6 characters"));
			Assertions.assertTrue(newUser.getUserConfirmPasswordInputFieldText().equals("min 6 characters"));
		} catch (Exception e) {
			logger.error("Error while testing - TestCase -12 - Validate All Fields");
			e.printStackTrace();
		}
	}

	/**
	 * TestCase -13 - UserName with Special Characters - This method will validate
	 * that when the user id contains special characters , the system should
	 * successfully create the user
	 */
	@Test
	public void tc13_UserNameSpecialCharacters() {
		try {
			logger.info("TestCase -13 - UserName with Special Characters");
			String userName = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC) + "%@^%@^";
			String userEmail = UtilObj.generateRandomEmail();
			String password = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);

			newUser.generateNewUser(userName, userEmail, password, password);
			Assertions.assertTrue(allUsers.verifyNewUserButtonDisplayed());
			if (allUsers.getNewUserName().equals(userName)) {
				logger.info("New User Name Matched");
			}
			Assertions.assertTrue(allUsers.getNewUserName().equals(userName));

			if (allUsers.getNewUserEmailID().equals(userEmail)) {
				logger.info("New User Email ID Matched");
			}
			Assertions.assertTrue(allUsers.getNewUserEmailID().equals(userEmail));
			if (allUsers.getNewUserPassword().equals(password)) {
				logger.info("New User password matched");
			}
			Assertions.assertTrue(allUsers.getNewUserPassword().equals(password));
			Assertions.assertTrue(UserAPI.verifyUserDetails(GlobalData.searchUserURL, userName, userEmail, password));

		} catch (Exception e) {
			logger.error("Error while testing - TestCase -13 - UserName with Special Characters");
			e.printStackTrace();
		}
	}

	/**
	 * TestCase - 14 - Invalid Email Address -This method validate the error message
	 * displayed when an invalid email is entered
	 */
	@Test
	public void tc14_ValidateInvalidEmailErrorMessage() {
		try {
			String userName = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);
			String password = UtilObj.generateRandomString(8, UtilityCommon.Mode.ALPHANUMERIC);

			newUser.generateNewUser(userName, userName, password, password);
			logger.info("TestCase - 14 - Invalid Email Address");
			Assertions.assertTrue(newUser.getUserEmailErrorMessage().equals("Invalid email address"));
		} catch (Exception e) {
			logger.error("Error while testing -- TestCase - 14 - Invalid Email Address");
			e.printStackTrace();
		}
	}

}
