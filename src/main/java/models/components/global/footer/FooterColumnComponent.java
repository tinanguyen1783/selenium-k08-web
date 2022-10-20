package models.components.global.footer;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FooterColumnComponent extends Component  {

    private final static By headerSel = By.tagName("h3");
    private final static By linkSel = By.cssSelector("li a");

    public FooterColumnComponent(WebDriver driver, WebElement webElement)
    {
        super(driver, webElement);
    }

    public WebElement getHeaderElem() {

        return component.findElement(headerSel);
    }

    public List<WebElement> getLinksElem() {

        return component.findElements(linkSel);
    }
}
