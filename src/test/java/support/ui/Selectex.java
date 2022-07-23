package support.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Selectex extends Select {

    private final String OPTION_01 = "Option 1";
    public Selectex(WebElement element) {
        super(element);
    }

    public void SelectOption1(){

        selectByVisibleText(OPTION_01);
    }


}
