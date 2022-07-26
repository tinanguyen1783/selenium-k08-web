package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitForElementEnable;
import url.Urls;

import java.time.Duration;

public class DynamicControl {

    public static void main(String[] args) {

        WebDriver driverFactory = DriverFactory.getChromeDriver();

        try {
            //Navigate to target page
            driverFactory.get(Urls.baseUrl.concat(Urls.floatingSlug));

            // Dropdown locator and element
            By checkBoxSel = By.id("checkbox-example");
            By inputFormSel = By.id("input-example");

            WebElement checkBoxFormElem = driverFactory.findElement(checkBoxSel);
            WebElement checkBoxElem = checkBoxFormElem.findElement(By.cssSelector("input"));
            if(!checkBoxElem.isSelected()) checkBoxElem.click();
            Thread.sleep(1000);
            if(checkBoxElem.isSelected()) checkBoxElem.click();
            Thread.sleep(1000);

            WebElement inputFormElem = driverFactory.findElement(inputFormSel);
            WebElement inputElem = inputFormElem.findElement(By.cssSelector("input"));
            WebElement inputButtonElem = inputFormElem.findElement(By.cssSelector("button"));
            if(!inputElem.isEnabled()) inputButtonElem.click();

            WebDriverWait webDriverWait = new WebDriverWait(driverFactory, Duration.ofSeconds(20));
           // webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));

            webDriverWait.until(new WaitForElementEnable(By.cssSelector("#input1-example input")));

            inputElem.sendKeys("Mac met ah");
            Thread.sleep(1000);



        } catch (Exception e) {
            e.printStackTrace();
        }

        driverFactory.quit();
    }

}
