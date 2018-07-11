package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public class UtilityCommon {
	
    public enum Mode
    {
        ALPHA, ALPHANUMERIC, NUMERIC
    }
    
	/**
	 * This method will retrieve properties from the config file
	 * @param propertyName
	 * @return
	 */
	public String getProperties(String propertyName)
	{
		Properties prop = new Properties();
		InputStream input = null;

		try {

			System.out.println("Path for Config Data" + GlobalData.ConfigFilePath);
			input = new FileInputStream(GlobalData.ConfigFilePath);

			
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			return prop.getProperty(propertyName);
			

		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * This method will retrieve the project path
	 * @return
	 */
	public String getProjectPath()
	{
		return System.getProperty("user.dir");
		
	}
	
	
    /**
     * This method will generate the random string
     * @param length - String length
     * @param mode - Specify the mode - Numeric/ALPHA/ALPHANUMERIC
     * @return
     */
    public  String generateRandomString(int length, Mode mode)
    {

        StringBuilder buffer = new StringBuilder();
        String characters = "";

        switch (mode)
        {

            case ALPHA:
                characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                break;

            case ALPHANUMERIC:
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                break;

            case NUMERIC:
                characters = "1234567890";
                break;
        }
        char[] chars =characters.toCharArray();
        int charactersLength = characters.length();
        Random random = new Random();

        for (int i = 0; i < length; i++)
        {

            int index = random.nextInt(charactersLength);

            buffer.append(chars[index]);
        }
        return buffer.toString();
    }
    
    
    /**
     * This method will generate random email
     * @return
     */
    public String generateRandomEmail()
    {
    	return (generateRandomString(8, Mode.ALPHANUMERIC) + "@" +generateRandomString(8, Mode.ALPHA) +".com"); 
    }
}
