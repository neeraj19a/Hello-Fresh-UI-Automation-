package tests.base;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.PageGenerator;
import tests.utils.WebEventListener;
import utils.Log;
import utils.PropertyManager;
import utils.TestUtil;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public PageGenerator page;
    public WebDriver driver;
    DriverManager driverManager;
    //Using WebEventListener to capture all events in Logs
    private EventFiringWebDriver e_driver;
    private WebEventListener eventListener;

    @Parameters("browser")
    @BeforeTest
    public void startService(String browser) {
        Log.info("browser is " + browser);
        Log.info("Initializing driver for " + browser + " browser");
        Log.info("Test is starting on -->" + browser);
        driverManager = DriverManagerFactory.getManager(browser);
    }


    @Parameters("browser")
    @BeforeMethod
    public void setup(Method result, String browser) {

        initial(browser);
    }

    @Step("Opening Browser")
    public void initial(String browser) {

        if (browser.equalsIgnoreCase("CHROME")) {
            String os = System.getProperty("os.name").toLowerCase();
            System.out.println("Running on " + os + " Operating System");
            Log.info("Running on " + os + " Operating System");
            driver = driverManager.getDriver(browser);

        } else if (browser.equalsIgnoreCase("FIREFOX")) {
            driver = driverManager.getDriver(browser);

        }
        e_driver = new EventFiringWebDriver(driver);
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;

        driver.get(new PropertyManager().getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.MILLISECONDS);

        page = new PageGenerator(e_driver);

    }


    @Step("Taking Screenshot")
    @Attachment
    public byte[] screenShot() {
        // WebDriver driver = WebDriverFactory.getDriverForThread();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step("Closing Browser")
    @AfterMethod
    public void teardown(ITestResult result) {
        {
            if (result.getStatus() == ITestResult.FAILURE) {

                Log.info("=========Test Case " + result.getName() + " Failed=======");
                Log.info("Taking Screenshot");
                screenShot();
            } else {
                Log.info("In After Method, Test " + result.getName() + " is Ending");
                Log.info("Taking Screenshot");
                screenShot();
            }
            driverManager.quitDriver();
            driverManager.stopService();

        }
    }

    @Parameters("browser")
    @AfterTest
    public void stopService(String browser) {
        Log.info("browser is " + browser);
        Log.info("Stopping driver for " + browser + " browser");

        driverManager.stopService();
    }
}