package models.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@ComponentCssSelector(value = "#login")
public class LoginFormComponent {

    private final WebDriver driver;

    public LoginFormComponent(WebDriver webDriver) {
        this.driver = webDriver;
    }
    private final static By usernameSel = By.id("username");
    private final static By passwordSel = By.id("password");
    private final static By loginBtnSel = By.cssSelector("[type='submit']");


    public void setUsername(String username) {

        driver.findElement(usernameSel).sendKeys(username);
    }

    public void setPassword(String password) {

        driver.findElement(passwordSel).sendKeys(password);
    }

    public void clickLoginBtn() {

        driver.findElement(loginBtnSel).click();
    }
}
