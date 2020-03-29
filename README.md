# Hello Fresh-Assignment

 Web application with url http://automationpractice.com/index.php;
-  To Automate 3 test cases.


#  Technologies and Libraries Used
-**Java**

-**Selenium**

-**TestNG**

-**Maven**

-**JavaFaker** (Library to generate Fake Test Data for Testing)

-**SLF4J with log4j2** (For Logging)

-**Allure** (For Reporting)

#  Prerequisite
Java, Maven and Allure  should be installed on your machine.

**======Allure Setup======**

**On Windows**

For Windows, Allure is available from the **Scoop** commandline-installer.

To install Allure, download and install Scoop and then execute in the Powershell:

**scoop install allure**

**On MAC**

For Mas OS, automated installation is available via [Homebrew]([https://brew.sh/](https://brew.sh/))

**brew install allure**

**Check the installation**

Execute allure --version in console to make sure that allure is now available:

$ allure --version
2.13.2

## About Framework:
This framework is built on Design Pattern: Page Object Model(**POM**) with Page Factory and **WebDriverFactory**.

**Browsers:** It Supports Chrome and FireFox Browser.

**OS:** Windows and MAC.

**Parallel Execution:** Yes you can run the Test cases in parallel using TestNG.xml.

> Browsers are picked as a Parameter from TestNG.xml file 

## WebDriver Factory:
 **DriverManagerFactory:** This class calls **ChromeDriverManager** or **FirefoxDriverManager** based on the browser you are executing your test case.

>  **ChromeDriverManager** and **FirefoxDriverManager** extends the
> **DriverManager** class

**DriverManager** This class provides instance of WebDriver

## Important Classes ,Files and Folders:

**PageGenerator:** It returns new instance of the Page.

**Base Page:** This is Base Class for all the pages, it contains all basic functions required while Automating Pages.

**PropertyManager:** To Load Property file **configuration.properties** ( it contains Application URL and existing username and password)

**ChromeDriver and GeckoDriver:** These are present in the framework under the resources. 

**ChromeDriver for MAC:** Present under path (src/test/java/resources/mac/chromedriver)

**GeckoDriver for MAC:** Present under path (src/test/java/resources/mac/geckodriver)

> Drivers are loaded based on the OS i.e.  based on the tests are executing on Windows or MAC

**BaseTest:** This class takes care of invoking the test case, creating the drivers based on OS(Windows or MAC) ,Quiting the Browser after Test Execution and Taking Screenshots.

> To capture WebDriver Events we are using **WebEventListener** class which extends **BaseTest** and  implements **WebDriverEventListener**

## Test Cases:
**HFTests**: All 3 Test cases(**signInTest,logInTest and checkoutTest**) are present in **HFTests** class

## How to Run Test cases:

Go To **TestNG.xml** file present under path( src/test/TestNG.xml)

Make Sure you have configurations to run parallel Tests and the browsers mentioned in parameter as following:

>   suite thread-count="2" name="Suite" parallel="tests"
>   parameter name ="browser" value="CHROME"

**Steps To Run the Test cases and generate Execution Report:**

Go To the Project location under command line or terminal:

**Enter command:**
 mvn clean test

> Wait for Tests to Execute till you  see something like
> Results:
[INFO]
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0

***Now Test cases have run successfully lets generate report***

**Run below command to Generate Reports using Allure**
allure serve allure-results

Wait till you see till you see something like

> Generating report to temp directory...
Report successfully generated to C:\Users\T460\AppData\Local\Temp\3580264047973062799\allure-report
Starting web server...

Now a new browser instance should be invoked and you should see Test Execution Reports
