package driver;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {

    public static WebDriver getChromeDriver() {

        String chromeDriverLocation = "";

        if (OS.isFamilyWindows())
            chromeDriverLocation = System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe";
        if (OS.isFamilyMac())
            chromeDriverLocation = System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver";
        if (chromeDriverLocation.isEmpty()) throw new IllegalArgumentException("Cannot detect OS type");

        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;


    }
}
