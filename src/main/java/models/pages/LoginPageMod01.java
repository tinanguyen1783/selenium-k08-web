package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageMod01 {

    private final WebDriver driver;
    private final static By usernameSel = By.id("username");
    private final static By passwordSel = By.id("password");
    private final static By loginBtnSel = By.cssSelector("[type='submit']");

    public LoginPageMod01(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getUsername() {

        return driver.findElement(usernameSel);
    }

    public WebElement getPassword() {

        return driver.findElement(passwordSel);
    }

    public WebElement getLoginBtn() {

        return driver.findElement(loginBtnSel);
    }
}
