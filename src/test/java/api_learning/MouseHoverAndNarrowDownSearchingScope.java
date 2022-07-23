package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MouseHoverAndNarrowDownSearchingScope implements url.Urls {

    public final static By figureSel = By.className("figure");
    public final static By nameSel = By.cssSelector("h5");
    public final static By linkSel = By.cssSelector("a");

    public static void main(String[] args) {

        WebDriver driverFactory = DriverFactory.getChromeDriver();

        try {
            //Navigate to target page
            driverFactory.get(url.Urls.baseUrl.concat(url.Urls.hoverSlug));
            List<WebElement> figureElem = driverFactory.findElements(figureSel);
            if (figureElem.isEmpty()) throw new RuntimeException("There is no profile image display.");
            //Define action object
            Actions actions = new Actions(driverFactory);

            for (WebElement figureEle : figureElem) {
                WebElement nameElem = figureEle.findElement(nameSel);
                WebElement linkElem = figureEle.findElement(linkSel);

                System.out.println("Profile : " + nameElem.getText() + nameElem.isDisplayed());
                System.out.println("Link : " + linkElem.getText() + linkElem.isDisplayed());
                //after mousehover;
                actions.moveToElement(figureEle).perform();
                System.out.println("Profile : " + nameElem.getText() + nameElem.isDisplayed());
                System.out.println("Link : " + linkElem.getText() + linkElem.isDisplayed());

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        driverFactory.quit();
    }
}
