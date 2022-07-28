package pom_tests;

import driver.DriverFactory;
import models.components.LoginFormComponent;
import models.pages.LoginPageMod03;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class LoginTestMod03 {


    public static void main(String[] args) {

        WebDriver driverFactory = DriverFactory.getChromeDriver();

        try {

            driverFactory.get(Urls.baseUrl.concat(Urls.loginSlug));


            LoginPageMod03 loginPageMod03 = new LoginPageMod03(driverFactory);
            LoginFormComponent loginFormComp = loginPageMod03.getloginFormComp();
            loginFormComp.setUsername("tomsmith");
           loginFormComp.setPassword("SuperSecretPassword!");
            loginFormComp.clickLoginBtn();

            Thread.sleep(2000);

        } catch (Exception e) {

            e.printStackTrace();
        }
        driverFactory.quit();
    }
}
