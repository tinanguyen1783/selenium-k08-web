package pom_tests;

import driver.DriverFactory;
import models.pages.LoginPageMod02;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class LoginTestMod02 {


    public static void main(String[] args) {

        WebDriver driverFactory = DriverFactory.getChromeDriver();

        try {

            driverFactory.get(Urls.baseUrl.concat(Urls.loginSlug));

            LoginPageMod02 loginPageMod02 = new LoginPageMod02(driverFactory);
            loginPageMod02.setUsername("tomsmith");
            loginPageMod02.setPassword("SuperSecretPassword!");
            loginPageMod02.clickLoginBtn();

            Thread.sleep(2000);

        } catch (Exception e) {

            e.printStackTrace();
        }
        driverFactory.quit();
    }
}
