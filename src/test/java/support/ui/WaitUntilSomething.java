package support.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitUntilSomething implements ExpectedCondition<Boolean> {

    public By cssSel;

    public WaitUntilSomething(By cssSel) {
        this.cssSel = cssSel;
    }

    @Override
    public Boolean apply(WebDriver webDriver) {

        return webDriver.findElement(cssSel).getText().equals("dfdf") ;
    }
}
