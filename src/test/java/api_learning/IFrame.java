package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import url.Urls;

public class IFrame implements url.Urls {

    public static void main(String[] args) {

        WebDriver driverFactory = DriverFactory.getChromeDriver();

        try {
            //Navigate to target page


            driverFactory.get(url.Urls.baseUrl.concat(Urls.iframeSlug));
            By iFrame = By.cssSelector("[Id$='ifr']");
            WebElement iFrameElm = driverFactory.findElement(iFrame);
            driverFactory.switchTo().frame(iFrameElm);


            WebElement editorInputElm = driverFactory.findElement(By.id("tinymce"));
            editorInputElm.click();
            editorInputElm.clear();
            editorInputElm.sendKeys("This is new text");
            Thread.sleep(3000);
            driverFactory.switchTo().defaultContent();
            driverFactory.findElement(By.linkText("Elemental Selenium")).click();
            Thread.sleep(2000);


        } catch (Exception e) {
            e.printStackTrace();
        }

        driverFactory.quit();
    }
}
