package test_flows.global;

import models.components.global.TopMenuComponent;
import models.components.global.TopMenuComponent.CatItemsComponent;
import models.components.global.TopMenuComponent.MainCatItems;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.FooterComponent;
import models.pages.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import url.Urls;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FooterTestFlow {

    private final WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent(){

        BasePage basePage = new BasePage(driver);
        FooterComponent footerComponent = basePage.getFooterComponent();

        verifyInformationColumn(footerComponent.getInformationColumn());
       // verifyCustomerColumn(footerComponent.getCustomerServiceColumn());
      //  verifyAccountColumn(footerComponent.getAccountColumn());
       // verifyFollowColumn(footerComponent.getFollowColumn());
    }

    private void verifyInformationColumn(FooterColumnComponent footerColumnComponent) {

        List<String> expectedLinkText = Arrays.asList("Sitemap", "Shipping & Returns", "Privacy Notice", " Conditions of Use", "About us", "Contact us");;
        List<String> expectedHrefs = Arrays.asList(Urls.homePageUrl+"/sitemap", "Urls.homePageUrl+/shipping-returns",Urls.homePageUrl+"privacy-policy",Urls.homePageUrl+"/condition-of-use",Urls.homePageUrl+ "/about-us", Urls.homePageUrl+"/contactus");
        verifyFooterColumn(footerColumnComponent,expectedLinkText, expectedHrefs );
    }
    private void verifyCustomerColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkText = new ArrayList<>();
        List<String> expectedHrefs =new ArrayList<>();
        verifyFooterColumn(footerColumnComponent,expectedLinkText, expectedHrefs );
    }
    private void verifyAccountColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkText = new ArrayList<>();
        List<String> expectedHrefs =new ArrayList<>();
        verifyFooterColumn(footerColumnComponent,expectedLinkText, expectedHrefs );
    }
    private void verifyFollowColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkText = new ArrayList<>();
        List<String> expectedHrefs =new ArrayList<>();
        verifyFooterColumn(footerColumnComponent,expectedLinkText, expectedHrefs );
    }

    public void verifyProductCatFooterComponent(){
// Random pickup an item
        BasePage basePage = new BasePage(driver);
        TopMenuComponent topMenuComponent = basePage.getTopMenuComponent();
        List<TopMenuComponent.MainCatItems> mainCatItems = topMenuComponent.getMainCatItemsElem();
        if(mainCatItems.isEmpty()) Assert.fail("Top menu items is empty");

        MainCatItems randomMainCatItems = mainCatItems.get(new SecureRandom().nextInt(mainCatItems.size()-1));
        String randomCatHref = randomMainCatItems.getCatItemLinkElem().getAttribute("href");

        // Get sublist
        List<CatItemsComponent> catItemsComponents = randomMainCatItems.getCatItemsComponent();
        System.out.println("Links ne"+  catItemsComponents.size());
        if(catItemsComponents.isEmpty())  randomMainCatItems.getCatItemLinkElem().click();
        else {
            CatItemsComponent randomCatItemComponent = catItemsComponents.get(new SecureRandom().nextInt(catItemsComponents.size() - 1));
            randomCatHref = randomCatItemComponent.getComponent().getAttribute("Href");
           randomCatItemComponent.getComponent().click();

        }

        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            webDriverWait.until(ExpectedConditions.urlContains(randomCatHref));
        }catch(TimeoutException e) {Assert.fail("Target page are not matching");}
        //Verify Footer Component;
        verifyFooterComponent();


    }
    public static void verifyFooterColumn(FooterColumnComponent footerColumnComponent, List<String> expectedLinkTexts, List<String> expectedHrefs) {


        List<String> actualLinkText = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();
        for (WebElement links : footerColumnComponent.getLinksElem()) {
            actualLinkText.add(links.getText());
            actualHrefs.add(links.getAttribute("href"));
        }

        if(actualHrefs.isEmpty() || actualLinkText.isEmpty()) Assert.fail(" [Err] Hrefs or Link is empty on Footer column.");

        // Verify links text
        Assert.assertEquals(actualLinkText, actualLinkText, "Link Text are different.");
        //Verify Hrefs
        //Assert.assertEquals(actualHrefs,expectedHrefs, "Hrefs are different");



    }
}
