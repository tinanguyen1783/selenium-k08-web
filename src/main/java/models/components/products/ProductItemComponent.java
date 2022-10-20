package models.components.products;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".product-item")
public class ProductItemComponent extends Component {

    public final static By productTitleSelector = By.cssSelector(".product-title");
    public ProductItemComponent(WebDriver driver, WebElement webElement) {
        super(driver, webElement);
    }
    public WebElement getProductTitleElem(){

        return component.findElement(productTitleSelector);
    }
}
