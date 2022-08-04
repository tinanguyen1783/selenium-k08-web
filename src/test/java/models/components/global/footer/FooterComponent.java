package models.components.global.footer;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".footer")
public class FooterComponent extends Component {


    public FooterComponent(WebDriver driver, WebElement webElement) {
        super(driver, webElement);
    }

    public InformationColumnComponent getInformationColumn(){

        return findComponent(InformationColumnComponent.class, driver);
    }

    public CustomerServiceColumnComponent getCustomerServiceColumn(){

        return findComponent(CustomerServiceColumnComponent.class, driver);
    }

    public AccountColumnComponent getAccountColumn(){

        return findComponent(AccountColumnComponent.class,driver);
    }

    public FollowColumnComponent getFollowColumn(){

        return findComponent(FollowColumnComponent.class,driver);
    }
}
