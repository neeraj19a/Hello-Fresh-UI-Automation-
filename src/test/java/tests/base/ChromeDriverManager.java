package tests.base;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.Log;

import java.io.File;

public class ChromeDriverManager extends DriverManager {

    private ChromeDriverService chService;

    @Override
    public void startService() {
        String os = System.getProperty("os.name").toLowerCase();
        Log.info("OS value-->" + os);
        String separator = System.getProperty("file.separator");

        File file = null;
        if (os.toLowerCase().contains("windows")) {

            String chromePathwindows = System.getProperty("user.dir")
                    + separator + "src"
                    + separator + "test"
                    + separator + "java"
                    + separator + "resources"
                    + separator + "windows"
                    + separator + "chromedriver.exe";
            System.out.println("Setting Up Chrome On Windows on the path-->" + chromePathwindows);
            Log.info("Setting Up Chrome On Windows on the path-->" + chromePathwindows);

            System.setProperty("webdriver.chrome.driver", chromePathwindows);

            file = new File(chromePathwindows);

        } else if (os.toLowerCase().contains("mac")) {
            String chromePathmac = System.getProperty("user.dir")
                    + separator + "src"
                    + separator + "test"
                    + separator + "java"
                    + separator + "resources"
                    + separator + "mac"
                    + separator + "chromedriver";
            System.out.println("Setting Up Chrome On MAC on the path-->" + chromePathmac);
            Log.info("Setting Up Chrome On MAC on the path-->" + chromePathmac);
            System.setProperty("webdriver.chrome.driver", chromePathmac);

            file = new File(chromePathmac);
        }

        if (null == chService) {
            try {
                chService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(file)
                        .usingAnyFreePort()
                        .build();
                chService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void stopService() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (null != chService && chService.isRunning())
            chService.stop();
    }


    @Override
    public void createDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("disable-infobars");
        capabilities.setPlatform(Platform.ANY);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(chService, capabilities);

    }

    @Override
    public WebDriver getDriverInstance() {
        if (null == driver) {
            startService();
            createDriver();
        }
        return driver;
    }

}