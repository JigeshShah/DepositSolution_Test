package API;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * @author Jigesh Shah
 * This Class will manage User specific operation
 */
public class UserAPI extends APICommon {

	static final Logger logger = LogManager.getLogger(UserAPI.class.getName());
	static List<String> UserList;
	static List<String> EmailList;
	static List<String> PasswordList;

	/**
	 * This method will verify the User details
	 * @param url
	 * @param UserName
	 * @param EmailId
	 * @param Password
	 * @return
	 * @throws Exception
	 */
	public static boolean verifyUserDetails(String url, String UserName, String EmailId, String Password)
			throws Exception {
		boolean isUserPresent_Email = false;
		boolean isUserPresent_UserId = false;
		boolean isUserPresent_Password = false;

		String response = executeAPIService(url, "GET");
		JsonReader reader = Json.createReader(new StringReader(response));
		JsonArray userArray = reader.readArray();
		reader.close();
		
		UserList = addJsonDataToList(userArray, "name");
		EmailList = addJsonDataToList(userArray, "email");
		PasswordList = addJsonDataToList(userArray, "password");

		isUserPresent_UserId = verifyUserNamePresent(UserName);
		isUserPresent_Email = verifyEmailIdPresent(EmailId);
		isUserPresent_Password = verifyPasswordPresent(Password);

		if (isUserPresent_UserId && isUserPresent_Email && isUserPresent_Password)
		{
			logger.info("Records found using the API");
			return true;
		} else {
			logger.error("Unable to find the records from the API");
			return false;
		}
	}

	/**
	 * This method will delete all the users using the delete user API
	 * @param url - URL to delete the 
	 * @return true if the delete operation is successful else will return false
	 * @throws Exception
	 */
	public static boolean deleteAllUsers(String url) throws Exception {
		String response = executeAPIService(url, "DELETE");
		
		if(response.equals("||FAILED||"))
		{
			logger.error("Unable to delete the records from the API");
			return false;
		}
		else
		{
			logger.info("Records deleted successfully using the API");
			return true;
		}
	}

	/**
	 * This method will add Json data from the API to an Array List
	 * @param userArray - JSON array that needs to be converted to list 
	 * @param jsonKey  - JSON Key that needs to be retrieved
	 * @return
	 */
	public static List<String> addJsonDataToList(JsonArray userArray, String jsonKey) throws Exception  {
		List<String> tempJsonArrayList = new ArrayList<String>();
		for (JsonValue jsonVal : userArray) {
			tempJsonArrayList.add(((JsonObject) jsonVal).getString(jsonKey));

		}

		return tempJsonArrayList;
	}

	/**
	 * This method will search the required String from the array list
	 * @param SearchList -- List where key needs to be searched
	 * @param SearchValue -- Value that needs to be checked
	 * @return true if the string is found
	 * @throws Exception
	 */
	public static boolean searchStringFromArrayList(List<String> SearchList, String SearchValue) throws Exception  {
		boolean isStringAvailable = false;
		for (String tempSearchValue : SearchList) {
			if (tempSearchValue.equals(SearchValue)) {
				isStringAvailable = true;
				break;
			}

		}

		return isStringAvailable;

	}

	/**
	 * This method will verify if  username is present in the list or not
	 * @param UserName
	 * @return true if present else will return false
	 * @throws Exception
	 */
	public static boolean verifyUserNamePresent(String UserName) throws Exception {
		return searchStringFromArrayList(UserList, UserName);
	}

	/**
	 * This method will verify if EmailId is present in the list or not
	 * @param Password
	 * @return true if present else will return false
	 * @throws Exception
	 */
	public static boolean verifyEmailIdPresent(String EmailId) throws Exception {
		return searchStringFromArrayList(EmailList, EmailId);
	}

	/**
	 * This method will verify if password  is present in the list or not
	 * @param UserName
	 * @return true if present else will return false
	 * @throws Exception
	 */
	public static boolean verifyPasswordPresent(String Password) throws Exception {
		return searchStringFromArrayList(PasswordList, Password);
	}
}
