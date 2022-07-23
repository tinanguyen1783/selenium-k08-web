package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkTextInteraction {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            driver.get("https://the-internet.herokuapp.com/login");
            By poweredByLinkTextSel = By.linkText("Elemental Selenium");

            WebElement poweredByLinkTextElem = driver.findElement(poweredByLinkTextSel);
            poweredByLinkTextElem.submit();
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }


}

