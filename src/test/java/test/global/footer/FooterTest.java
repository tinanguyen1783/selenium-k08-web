package test.global.footer;

import driver.DriverFactory;
import models.components.global.footer.CustomerServiceColumnComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.InformationColumnComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import url.Urls;

public class FooterTest {


    // @Test(priority = 1, dependsOnMethods = {"testFooterCategoryPage"})
    public void testFooterHomePage() {

        WebDriver driver = DriverFactory.getChromeDriver();
        driver.get(Urls.homePageUrl);
        try {

            HomePage homePage = new HomePage(driver);
            InformationColumnComponent informationColumnComponent = homePage.getFooterComponent().getInformationColumn();
            testFooterColumn(informationColumnComponent);

            CustomerServiceColumnComponent customerServiceColumnComponent = homePage.getFooterComponent().getCustomerServiceColumn();

            testFooterColumn(customerServiceColumnComponent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    @Test(priority = 3)
    private static void testFooterCategoryPage() {
        String actualResult = "a", expectedResult = "b";
        // Verifier.verifyEquals(actualResult,expectedResult);
        // Hart assertion.

        Assert.assertEquals(actualResult, expectedResult, "[ERR] Welcome message is incorrect jjjj");
        System.out.println("Hello");
        Assert.assertTrue(actualResult.equals(expectedResult), "....incorrect");
        Assert.fail();
        Assert.fail("...fail");

    }

    @Test(priority = 2)
    private static void testFooterRegisterPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1,2);
        softAssert.assertEquals(true,true);
        softAssert.assertEquals(2,4);

        softAssert.assertAll();

        System.out.println("hello");
    }

    @Test(priority = 4)
    private static void testFooterLoginPage() {
    }

    public static void testFooterColumn(FooterColumnComponent footerColumnComponent) {


        System.out.println(footerColumnComponent.getHeaderElem().getText());

        footerColumnComponent.getLinksElem().forEach(links -> {

            System.out.println(links.getText());
            System.out.println(links.getAttribute("href"));

        });

    }
}
