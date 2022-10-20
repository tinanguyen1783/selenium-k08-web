package models.components.products;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@ComponentCssSelector(".product-grid")
public class ProductGridComponent extends Component {


    public ProductGridComponent(WebDriver driver, WebElement webElement) {
        super(driver, webElement);
    }

    public List<ProductItemComponent> getProductItemList() {
        return findComponents(ProductItemComponent.class, driver);
    }

}
