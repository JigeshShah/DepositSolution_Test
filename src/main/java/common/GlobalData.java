package common;

public class GlobalData {

	public static String ConfigFilePath="";
	public static String HomePageURL="";
	public static int ElementTimeout=10;
	public static String searchUserURL="";
	public static String DeleteUserURL="";
	

	
	/**
	 * This method will initialize all the global data that is required during test run
	 */
	public void setGlobalData()
	{
		UtilityCommon utilobj= new UtilityCommon();
		ConfigFilePath=System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";
		HomePageURL=utilobj.getProperties("url");
		ElementTimeout=Integer.parseInt(utilobj.getProperties("ElementTimeout"));
		searchUserURL=utilobj.getProperties("ServiceURL") + utilobj.getProperties("SearchURL");
		DeleteUserURL=utilobj.getProperties("ServiceURL") + utilobj.getProperties("DeleteURL");

	}
}
