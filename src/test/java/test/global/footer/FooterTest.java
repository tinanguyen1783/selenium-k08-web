package test.global.footer;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.global.FooterTestFlow;
import url.Urls;

public class FooterTest extends BaseTest {


    // @Test(priority = 1, dependsOnMethods = {"testFooterCategoryPage"})


    @Test
    private static void testFooterCategoryPage() {

        driver.get(Urls.homePageUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyProductCatFooterComponent();


       /* String actualResult = "a", expectedResult = "b";
        // Verifier.verifyEquals(actualResult,expectedResult);
        // Hart assertion.

        Assert.assertEquals(actualResult, expectedResult, "[ERR] Welcome message is incorrect jjjj");
        System.out.println("Hello");
        Assert.assertTrue(actualResult.equals(expectedResult), "....incorrect");
        Assert.fail();
        Assert.fail("...fail");
*/
    }

@Test
    public void testFooterHomePage() {
    Assert.fail();
        driver.get(Urls.homePageUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyProductCatFooterComponent();
    }


}
