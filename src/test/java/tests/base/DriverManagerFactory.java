package tests.base;

public class DriverManagerFactory {
    public static DriverManager getManager(String browser) {

        DriverManager driverManager = null;

        switch (browser) {
            case "CHROME":
                driverManager = new ChromeDriverManager();
                break;
            case "FIREFOX":
                 driverManager = new FirefoxDriverManager();
                break;

        }
        return driverManager;


    }
}