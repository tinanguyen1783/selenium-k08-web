package models.components.global.footer;

import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".column.customer-service")

public class CustomerServiceColumnComponent extends FooterColumnComponent {
    public CustomerServiceColumnComponent(WebDriver driver, WebElement webElement) {
        super(driver, webElement);
    }
}
