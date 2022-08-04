package test.global.footer;

import driver.DriverFactory;
import models.components.global.footer.CustomerServiceColumnComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.InformationColumnComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class FooterTest {

    public static void main(String[] args) {

        WebDriver driverFactory = DriverFactory.getChromeDriver();


        testFooterHomePage(driverFactory);
       /* testFooterCategoryPage(driverFactory);
        testFooterRegisterPage(driverFactory);
        testFooterLoginPage(driverFactory);
*/
        driverFactory.quit();
    }

    private static void testFooterHomePage(WebDriver driver) {
        driver.get(Urls.homePageUrl);
        HomePage homePage = new HomePage(driver);
        InformationColumnComponent informationColumnComponent = homePage.getFooterComponent().getInformationColumn();
        testFooterColunm(informationColumnComponent);

        CustomerServiceColumnComponent customerServiceColumnComponent = homePage.getFooterComponent().getCustomerServiceColumn();

       testFooterColunm(customerServiceColumnComponent);

    }


    private static void testFooterCategoryPage(WebDriver driver) {
    }

    private static void testFooterRegisterPage(WebDriver driver) {
    }

    private static void testFooterLoginPage(WebDriver driver) {
    }

    public static void testFooterColunm(FooterColumnComponent footerColumnComponent){


        System.out.println(footerColumnComponent.getHeaderElem().getText());

        footerColumnComponent.getLinksElem().forEach(links ->{

            System.out.println(links.getText());
            System.out.println(links.getAttribute("href"));

        });

    }
}
