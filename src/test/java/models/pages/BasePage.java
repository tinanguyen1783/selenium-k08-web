package models.pages;

import models.components.Component;
import models.components.global.TopMenuComponent;
import models.components.global.footer.FooterComponent;
import models.components.global.header.HeaderComponent;
import models.components.products.ProductGridComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage extends Component {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        super(driver, driver.findElement(By.tagName("html")));
        this.driver = driver;


    }

    public HeaderComponent getHeaderComponent(){

        return findComponent(HeaderComponent.class,driver);
    }
    public TopMenuComponent getTopMenuComponent() {

        return findComponent(TopMenuComponent.class, driver);
    }

    public FooterComponent getFooterComponent() {

        return findComponent(FooterComponent.class, driver);

    }

    public ProductGridComponent getProductGridComponent() {

        return findComponent(ProductGridComponent.class, driver);
    }


}
