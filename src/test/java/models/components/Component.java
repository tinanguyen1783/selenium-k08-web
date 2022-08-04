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


        this.webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getCompSelector(componentClass)));
        List<WebElement> webElementList = this.component.findElements(getCompSelector(componentClass));

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
    public By getCompSelector(Class<? extends Component> componentClass){

        if(componentClass.isAnnotationPresent(ComponentCssSelector.class))
            return By.cssSelector(componentClass.getAnnotation(ComponentCssSelector.class).value());
        else if (componentClass.isAnnotationPresent(ComponentIdSelector.class)) {
            return By.id(componentClass.getAnnotation(ComponentIdSelector.class).value());

        } else if (componentClass.isAnnotationPresent(ComponentXpathSelector.class)) {

            return By.xpath(componentClass.getAnnotation(ComponentXpathSelector.class).value());

        }
        else throw new IllegalArgumentException("Component class" + componentClass + " must have " + ComponentXpathSelector.class.getSimpleName() + "or" + ComponentIdSelector.class.getSimpleName() + "or" + ComponentCssSelector.class.getSimpleName());

    }
}
