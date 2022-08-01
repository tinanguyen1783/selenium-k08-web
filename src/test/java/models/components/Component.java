package models.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Constructor;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Component {

    protected WebDriver driver;
    protected WebElement component;
    protected WebDriverWait webDriverWait;

    public Component(WebDriver driver, WebElement webElement) {
        this.driver = driver;
        this.component = webElement;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public WebElement findElement(By by) {

        return component.findElement(by);
    }

    public List<WebElement> findElements(By by) {

        return component.findElements(by);
    }

    public <T extends Component> T findComponent(Class<T> componentClass, WebDriver driver) {

        return findComponents(componentClass, driver).get(0);
    }

    public <T extends Component> List<T> findComponents(Class<T> componentClass, WebDriver driver) {
// get component selector
        String cssSelector;
        try {

            cssSelector = componentClass.getAnnotation(ComponentCssSelector.class).value();
        } catch (Exception e) {
            throw new IllegalArgumentException("#Err The componentClass must have a css selector.");
        }

        this.webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssSelector)));
        List<WebElement> webElementList = this.component.findElements(By.cssSelector(cssSelector));

        // Define component class constructor
        Class<?>[] params = new Class[]{WebDriver.class, WebElement.class};
        Constructor<T> constructor;

        try {
            constructor = componentClass.getConstructor(params);

        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "ERR The component must have  a  contructor with param"
                            + Arrays.toString(params));
        }


        // convert all webelement -> conponent
        List<T> componennts = webElementList.stream().map(webElement -> {

            try {

                return constructor.newInstance(driver, webElement);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }).collect(Collectors.toList());

        return componennts;
    }
}
