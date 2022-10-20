package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitMoreThanOneTab;
import url.Urls;

import java.time.Duration;

public class ExplicitWait {

    public static void main(String[] args) {

        WebDriver driverFactory = DriverFactory.getChromeDriver();

        try {
            driverFactory.get(Urls.baseUrl.concat(Urls.floatingSlug));
            By wrongBy = By.id("wrong");
            WebDriverWait webDriverWait = new WebDriverWait(driverFactory, Duration.ofSeconds(10));
            webDriverWait.until(new WaitMoreThanOneTab());
            //webDriverWait.until(new WaitUntilSomething(By.cssSelector("sfd")));
            // webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(wrongBy));
            //   webDriverWait.until(ExpectedConditions.visibilityOf(driverFactory.findElement(wrongBy)));

        } catch (Exception e) {
            e.printStackTrace();
        }

        driverFactory.quit();
    }
}
