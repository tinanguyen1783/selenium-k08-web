package models.components.order;

import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@ComponentCssSelector(".product-essential")
public class StandardComputerComponent extends ComputerEssentialComponent {
    private static final By productAttibuteSel = By.cssSelector("Select[id^=\"product_attribute\"]");

    public StandardComputerComponent(WebDriver driver, WebElement webElement) {
        super(driver, webElement);
    }

    @Override
    public String selectProcessorType(String type) {

        final int PROCESSOR_DROPDOWN_INDEX = 0;
        WebElement processorDropdownElem = component.findElements(productAttibuteSel).get(PROCESSOR_DROPDOWN_INDEX);
        return selectOption(processorDropdownElem, type);
    }

    @Override
    public String selectRamType(String type) {
        final int RAM_DROPDOWN_INDEX = 1;
        WebElement ramDropdownElem = component.findElements(productAttibuteSel).get(RAM_DROPDOWN_INDEX);
        return selectOption(ramDropdownElem, type);
    }

    private String selectOption(WebElement dropdownElem, String type){

        Select select = new Select(dropdownElem);
        List<WebElement> allOption = select.getOptions();
        String fullStrOption = null;

        for (WebElement option : allOption) {
            String currentOptionText = option.getText();
            String optionTextWithoutSpaces = currentOptionText.trim().replace(" ", "");
            if (optionTextWithoutSpaces.startsWith(type)) {
                fullStrOption = currentOptionText;
                break;
            }
        }
        if (fullStrOption == null) {
            throw new RuntimeException("[ERR] The option" + type + "is not existing to select");
        }
        select.selectByVisibleText(fullStrOption);
        return fullStrOption;
    }
}
