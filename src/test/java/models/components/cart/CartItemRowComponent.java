package models.components.cart;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".cart-item-row")
public class CartItemRowComponent extends Component {

    private static final By unitPriceSel = By.cssSelector(".product-unit-price");
    private static final By quantityInputSel = By.cssSelector(".qty-input");
    private static final By subtotal = By.cssSelector(".product-subtotal");
    public CartItemRowComponent(WebDriver driver, WebElement webElement) {
        super(driver, webElement);
    }

    public double getUnitPrice(){
        return Double.parseDouble(component.findElement(unitPriceSel).getText().trim());
    }

    public double getQuantity(){
        return Double.parseDouble(component.findElement(quantityInputSel).getAttribute("value").trim());
    }

    public double getsubTotal(){

        return Double.parseDouble(component.findElement(subtotal).getText().trim());
    }

}
