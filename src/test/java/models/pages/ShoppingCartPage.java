package models.pages;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ShoppingCartPage extends  BasePage{

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public List<CartItemRowComponent> cartItemRowComponentList(){
        return findComponents(CartItemRowComponent.class,driver);
    }
public TotalComponent getTotalComp(){

        return findComponent(TotalComponent.class, driver);
}

}
