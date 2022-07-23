package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;

public class PageTitleandCurrentURL {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            driver.get("https://the-internet.herokuapp.com/login");
            System.out.println(driver.getTitle());
            System.out.println(driver.getCurrentUrl());


            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }


}

