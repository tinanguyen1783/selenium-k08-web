package models.components.global.footer;

import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".column.information")

public class AccountColumnComponent extends FooterColumnComponent {
    public AccountColumnComponent(WebDriver driver, WebElement webElement) {
        super(driver, webElement);
    }
}
