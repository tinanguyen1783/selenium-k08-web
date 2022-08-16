package test.global.footer;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test_flows.global.FooterTestFlow;
import url.Urls;

public class FooterTest {


    // @Test(priority = 1, dependsOnMethods = {"testFooterCategoryPage"})
    public void testFooterHomePage() {}
    public void test1() {
    }
@Test
    private static void testFooterCategoryPage() {
        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            driver.get(Urls.homePageUrl);
            FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
            footerTestFlow.verifyProductCatFooterComponent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
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
    private static void testFooterRegisterPage() {  }
    private static void testFooterLoginPage() {
    }


}
