package models.components.global.header;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".header")
public class HeaderComponent extends Component {

    private final static By shoppingCartLinkSel = By.cssSelector("#topcartlink");

    public HeaderComponent(WebDriver driver, WebElement webElement) {
        super(driver, webElement);
    }

    public void clickOnShoppingCartLink() {
        WebElement shoppingCartLinkElem = component.findElement(shoppingCartLinkSel);
        scrollUpToElement(shoppingCartLinkElem);
        shoppingCartLinkElem.click();
    }
}
