package tests.base;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.Log;

import java.io.File;
import java.io.IOException;

public class FirefoxDriverManager extends DriverManager {

    private GeckoDriverService geckoDriverService;

    @Override
    public void startService() {
        String os = System.getProperty("os.name").toLowerCase();
        Log.info("OS value-->" + os);
        String separator = System.getProperty("file.separator");

        File file = null;
        if (os.toLowerCase().contains("windows")) {
            String firefoxPathwindows = System.getProperty("user.dir")
                    + separator + "src"
                    + separator + "test"
                    + separator + "java"
                    + separator + "resources"
                    + separator + "windows"
                    + separator + "geckodriver.exe";
            System.out.println("Setting Up Firefox On Windows on the path-->" + firefoxPathwindows);
            Log.info("Setting Up Firefox On Windows on the path-->" + firefoxPathwindows);
            System.setProperty("webdriver.gecko.driver", firefoxPathwindows);

            file = new File(firefoxPathwindows);
        } else if (os.toLowerCase().contains("mac")) {
            String firefoxPathmac = System.getProperty("user.dir")
                    + separator + "src"
                    + separator + "test"
                    + separator + "java"
                    + separator + "resources"
                    + separator + "mac"
                    + separator + "geckodriver";
            System.out.println("Setting Up Firefox On Windows on the path-->" + firefoxPathmac);
            Log.info("Setting Up Firefox On Windows on the path-->" + firefoxPathmac);
            System.setProperty("webdriver.gecko.driver", firefoxPathmac);

            file = new File(firefoxPathmac);

        }

        if (null == geckoDriverService) {
            try {
                geckoDriverService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(file)
                        .usingAnyFreePort()
                        .build();
                geckoDriverService.start();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            Log.info("GeckoDriverService Started");
        }
    }

    @Override
    public void stopService() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (null != geckoDriverService && geckoDriverService.isRunning()) {
            geckoDriverService.stop();
            Log.info("GeckoDriverService Stopped");
        }
    }

    @Override
    public void createDriver() {

        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setPlatform(Platform.ANY);
        final FirefoxOptions options = new FirefoxOptions()
                .setLogLevel(FirefoxDriverLogLevel.ERROR); //to stop the debug spam
        // add additional options here as required
        capability.merge(options);
        this.driver = new FirefoxDriver(options);
        Log.info("FirefoxDriver created");

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