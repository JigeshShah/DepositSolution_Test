Maven + Junit + Selenium Deposit Test Solution

Primary Technologies Used:

Java , Maven , Selenium and JUnit 4.12

Other Libraries Used:

Log4j2 and WebDriver Manager


Description
The Automation framework will test user creation and validation flow on UI as well as API. Whenever the test execution starts it will delete the list of existing users. Post deletion , it will execute the test cases and post execution it will close the driver window

Browsers supported 
As this framework is using the WebDriver Manager library, it supports all the browsers that are provided by this library. This project has been tested in chrome driver

How to execute this project 
You can directly import this project in your workspace and start executing it by selecting Run as > Maven test on the project explorer

Features
 - Extensible and Reusable : This project is easily extensible and reusable  as most of the common classes created are generic and can be easily reused.
 - Maintainable and configurable : This  framework is easy to maintain and configure as most of common changes can be specified in the config file. For example, if you want to change the browser, all that is required is to change the value in the config.properties and no need to modify any value in the code.
 - Logging : Logging has been added for each and every action that takes places which will help to debug any error/issues that occur during the test run.
 - Page objects: Page object mechanism has been used to interact with the target application.


  
