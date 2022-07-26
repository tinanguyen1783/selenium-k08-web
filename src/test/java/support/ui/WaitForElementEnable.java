package support.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitForElementEnable implements ExpectedCondition<Boolean> {


    private By selector ;

    public WaitForElementEnable(By selector) {
        this.selector = selector;
    }

    @Override
    public Boolean apply(WebDriver webDriver) {
        return webDriver.findElement(selector).isEnabled() ;
    }

    @Override
    public String toString() {
        return "Waiting for element located by" + this.selector.toString();
    }
}
