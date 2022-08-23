package models.components.order;

import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".product-essential")
public class StandardComputerComponent extends ComputerEssentialComponent{
    public StandardComputerComponent(WebDriver driver, WebElement webElement) {
        super(driver, webElement);
    }

    @Override
    public String selectProcessorType(String type) {
        return null;
    }

    @Override
    public String selectRamType(String type) {
        return null;
    }
}
