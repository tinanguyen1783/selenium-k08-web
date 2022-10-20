package models.components.cart;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentCssSelector(".cart-footer .totals")
public class TotalComponent extends Component {

    public TotalComponent(WebDriver driver, WebElement webElement) {
        super(driver, webElement);
    }

    private static final By priceTableRowSel = By.cssSelector("table tr");
    private static final By priceTypeSel = By.cssSelector(".cart-total-left");
    private static final By priceValueSel = By.cssSelector(".cart-total-right");

    private static final By tosSel = By.cssSelector("#termsofservice");
    private static final By checkoutBtnSel = By.cssSelector("#checkout");

    public Map<String,Double> priceCategories(){

        Map<String,Double> priceCategories = new HashMap<>();
        List<WebElement> priceTableRowsElem = component.findElements(priceTableRowSel);
        for (WebElement tableRowElm : priceTableRowsElem) {
            WebElement priceTypeElem = tableRowElm.findElement(priceTypeSel);
            WebElement priceValueElem = tableRowElm.findElement(priceValueSel);

            String priceType = priceTypeElem.getText().trim().replace(":", "");
            double priceValue = Double.parseDouble(priceValueElem.getText().trim());
            priceCategories.put(priceType,priceValue);
        }
        return priceCategories;
    }

    public void agreeTOS(){

        component.findElement(tosSel).click();
    }
    public void clickOnCheckoutBtn(){
        component.findElement(checkoutBtnSel).click();
    }
}
