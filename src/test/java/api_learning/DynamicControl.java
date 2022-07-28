package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import url.Urls;

public class DynamicControl {

    public static void main(String[] args) {

        WebDriver driverFactory = DriverFactory.getChromeDriver();

        try {
            //Navigate to target page
            driverFactory.get(Urls.baseUrl.concat(Urls.dynamicControlSlug));

            // Dropdown locator and element
            By checkBoxSel = By.id("checkbox-example");
            By inputFormSel = By.id("input-example");

            WebElement checkBoxFormElem = driverFactory.findElement(checkBoxSel);

            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driverFactory;
            boolean isLocal = System.getenv("isLocal").equalsIgnoreCase("true");
            if (isLocal) {
                javascriptExecutor.executeScript("arguments[0].setAttribute('style', 'border: 4px solid red ;');", checkBoxFormElem);


            }
            //WebElement checkBoxElem = checkBoxFormElem.findElement(By.cssSelector("input"));
            // if(!checkBoxElem.isSelected()) checkBoxElem.click();
            //Thread.sleep(1000);
            //if(checkBoxElem.isSelected()) checkBoxElem.click();
            Thread.sleep(1000);

            ///  WebElement inputFormElem = driverFactory.findElement(inputFormSel);
            // WebElement inputElem = inputFormElem.findElement(By.cssSelector("input"));
            // WebElement inputButtonElem = inputFormElem.findElement(By.cssSelector("button"));
            //if(!inputElem.isEnabled()) inputButtonElem.click();

            //WebDriverWait webDriverWait = new WebDriverWait(driverFactory, Duration.ofSeconds(20));
            // webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));

            // webDriverWait.until(new WaitForElementEnable(By.cssSelector("#input-example input")));

            //inputElem.sendKeys("Mac met ah");
            // Thread.sleep(1000);


        } catch (Exception e) {
            e.printStackTrace();
        }

        driverFactory.quit();
    }

}
