package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.ui.Selectex;
import url.Urls;

public class Dropdown implements Urls {

    public static void main(String[] args) {

        WebDriver driverFactory = DriverFactory.getChromeDriver();

        try {
            //Navigate to target page
            driverFactory.get(Urls.baseUrl.concat(Urls.dropdownSlug));

            // Dropdown locator and element
            By dropdownSlt = By.id("Dropdown");
            WebElement dropdownElem = driverFactory.findElement(dropdownSlt);
            // select Dropdown.

            Selectex select = new Selectex(dropdownElem);
            // select by visible text
            select.SelectOption1();
            Thread.sleep(3000);

            //select by value
            select.selectByValue("2");
            Thread.sleep(3000);

            //select by index
            select.selectByIndex(1);
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driverFactory.quit();
    }
}
