package api_learning;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LaunchBrowser {

    public static void main(String[] args) {

        String currentProjectLocation = System.getProperty("user.dir");
        String chromeDriverLocation = "";

        if (OS.isFamilyMac()) chromeDriverLocation = currentProjectLocation + "src/test/resources/drivers/chromedriver";
        if (OS.isFamilyWindows())
            chromeDriverLocation = currentProjectLocation + "\\src\\test\\resources\\drivers\\chromedriver.exe";

        if (chromeDriverLocation.isEmpty()) {
            throw new IllegalArgumentException("Cannot detect OS type !");
        }

        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");

        WebDriver webDriver = new ChromeDriver(chromeOptions);
        //webDriver.manage().window().maximize();
        webDriver.get("http://google.com");
        try {

            Thread.sleep(3000);

        } catch (Exception e) {

            e.printStackTrace();
        }

        webDriver.quit();

    }
}
