package test.global.footer;

import driver.DriverFactory;
import models.components.products.ProductItemComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import url.Urls;

import java.util.List;

public class FeatureProductTest {

    public static void main(String[] args) {

        WebDriver driverFactory = DriverFactory.getChromeDriver();


        testFeatureProduct(driverFactory);
       /* testFooterCategoryPage(driverFactory);
        testFooterRegisterPage(driverFactory);
        testFooterLoginPage(driverFactory);
*/
        driverFactory.quit();
    }

    private static void testFeatureProduct(WebDriver driver) {
        DriverFactory.getChromeDriver().get(Urls.homePageUrl);
        HomePage homePage = new HomePage(driver);

        List<ProductItemComponent> productItemComponentList = homePage.getProductGridComponent().getProductItemList();

        System.out.println(productItemComponentList.size());
        productItemComponentList.forEach(productItemComponent -> {
            System.out.println(productItemComponent.getProductTitleElem().getText());
        });

    }



}
