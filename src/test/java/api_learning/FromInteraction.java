package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FromInteraction {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            driver.get("https://the-internet.herokuapp.com/login");
            By usernameSel = By.id("username");
            By passwordSel = By.name("password");
            By loginBtnSel = By.cssSelector("[type = 'submit']");
            WebElement usernameElem = driver.findElement(usernameSel);
            WebElement passwordElem = driver.findElement(passwordSel);
            WebElement buttonSubmit = driver.findElement(loginBtnSel);

            System.out.println("Attribute button " + buttonSubmit.getAttribute("type"));
            System.out.println(buttonSubmit.getCssValue("border-color"));

            usernameElem.clear();
            usernameElem.sendKeys("tomsmith");
            passwordElem.sendKeys("SuperSecretPassword!");
            buttonSubmit.submit();

            driver.navigate().back();
            driver.navigate().refresh();

            usernameElem = driver.findElement(usernameSel);
            passwordElem = driver.findElement(passwordSel);
            buttonSubmit = driver.findElement(loginBtnSel);
            usernameElem.sendKeys("ad");
            passwordElem.sendKeys("dfsdf");
            buttonSubmit.submit();


            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }


}

