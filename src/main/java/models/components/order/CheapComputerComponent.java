package models.components.order;

import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
@ComponentCssSelector(".product-essential")
public class CheapComputerComponent extends ComputerEssentialComponent{
    public CheapComputerComponent(WebDriver driver, WebElement webElement) {
        super(driver, webElement);
    }

    @Override
    public String selectProcessorType(String type) {
        return selectCompOption(type);
    }

    @Override
    public String selectRamType(String type) {
        return selectCompOption(type);
    }
}
