package pom_tests;

import driver.DriverFactory;
import models.pages.LoginPageMod01;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class LoginTestMod01 {

    public static void main(String[] args) {

        WebDriver driverFactory = DriverFactory.getChromeDriver();

        try {

            driverFactory.get(Urls.baseUrl.concat(Urls.loginSlug));
            LoginPageMod01 loginPageMod01 = new LoginPageMod01(driverFactory);
            loginPageMod01.getUsername().sendKeys("tomsmith");
            loginPageMod01.getPassword().sendKeys("SuperSecretPassword!");
            loginPageMod01.getLoginBtn().click();
            Thread.sleep(2000);

        } catch (Exception e) {

            e.printStackTrace();
        }
        driverFactory.quit();
    }
}
