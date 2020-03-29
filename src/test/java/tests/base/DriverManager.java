package tests.base;

import org.openqa.selenium.WebDriver;

/*
 * This class provides instance of WebDriver
 */
public abstract class DriverManager {

    protected WebDriver driver;

    protected abstract void startService();

    protected abstract void stopService();

    protected abstract void createDriver();

    public abstract WebDriver getDriverInstance();

    DriverManager driverManager;

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }


    public WebDriver getDriver(String browser) {

        if (null == driver) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driverManager = new ChromeDriverManager();
                    driver = driverManager.getDriverInstance();
                    break;

                case "firefox":
                    driverManager = new FirefoxDriverManager();
                    driver = driverManager.getDriverInstance();
                    break;
            }
        }

        return driver;
    }

}

