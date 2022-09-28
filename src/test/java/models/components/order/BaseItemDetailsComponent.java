package models.components.order;


import models.components.Component;
import org.openqa.selenium.By;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class BaseItemDetailsComponent extends Component {

    private final static By productPriceSel = By.cssSelector(".product-price");
    private final static By addToCartBtnSel = By.cssSelector(".add-to-cart-button");

    private final static By barNotificationContentSel = By.id("bar-notification");


    public BaseItemDetailsComponent(WebDriver driver, WebElement webElement) {
        super(driver, webElement);
    }

    public double getProductPrice(){

        String productPriceText = component.findElement(productPriceSel).getText().trim();
        return Double.parseDouble(productPriceText);
    }
    public void clickOnAddToCartBtn(){

        component.findElement(addToCartBtnSel).click();

    }

    public void waitUntilItemAddToCart(){

        String successMessage = "The product has been added to your shopping cart";
        try {
            webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(barNotificationContentSel, successMessage));
        }catch(TimeoutException e){clickOnAddToCartBtn(); }

    }



}
