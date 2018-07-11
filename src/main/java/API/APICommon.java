package API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Jigesh
 * This class will call the Service API
 *
 */
public class APICommon {

	static final Logger logger = LogManager.getLogger(APICommon.class.getName());	
	
	
	/**
	 * The method will execute the API Service
	 * @param url - The Name of URL to access the link
	 * @param MethodType -- GET/DELETE
	 * @return
	 * @throws Exception
	 */
	public static String executeAPIService(String url, String MethodType) throws Exception
	{
		String response = "";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod(MethodType);

		int responseCode = con.getResponseCode();

		if (responseCode == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine = "";
			

			while ((inputLine = in.readLine()) != null) {
				response = response + inputLine;
			}
			in.close();
		}
		else
		{
			response="||FAILED||";	
		}
		con.disconnect();

		logger.info("Service Response" + response);
		return response;
	}
}
