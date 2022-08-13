package models.components.global;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@ComponentCssSelector(".top-menu")
public class TopMenuComponent extends Component {
    public TopMenuComponent(WebDriver driver, WebElement webElement) {
        super(driver, webElement);
    }

    public List<MainCatItems> getMainCatItemsElem() {

        return findComponents(MainCatItems.class, driver);
    }


    @ComponentCssSelector(".top-menu > li")
    public static class MainCatItems extends Component {


        public MainCatItems(WebDriver driver, WebElement webElement) {
            super(driver, webElement);
        }

        public WebElement getCatItemLinkElem() {
            return component.findElement(By.tagName("a"));
        }

        public List<subListComponent> getSubListComponent() {

            Actions actions = new Actions(driver);
            actions.moveToElement(component).perform();
            return findComponents(subListComponent.class, driver);
        }
    }


    @ComponentCssSelector(".sublist li a")
    public static class subListComponent extends Component {


        public subListComponent(WebDriver driver, WebElement webElement) {
            super(driver, webElement);
        }
    }
}
