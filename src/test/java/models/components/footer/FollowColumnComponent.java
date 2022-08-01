package models.components.footer;

import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".column.follow-us")

public class FollowColumnComponent extends FooterColumnComponent {
    public FollowColumnComponent(WebDriver driver, WebElement webElement) {
        super(driver, webElement);
    }
}
