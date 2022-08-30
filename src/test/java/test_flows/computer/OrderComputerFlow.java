package test_flows.computer;

import models.components.cart.TotalComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import test_data.computer.ComputerData;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private ComputerData computerData;
    private final int quantity;
    private double totalItemPrice;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = 1;
    }

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData, int quantity) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = quantity;
    }

    public void buildCompSpecAndAddToCart() throws InterruptedException {

        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.getComputerComp(computerEssentialComponent);
        // Unselect all default option
        computerEssentialComp.unselectDefaultOption();
        String processorFullStr = computerEssentialComp.selectProcessorType(computerData.getProcessorType());
        double processorAdditionalPrice = extractAdditionalPrice(processorFullStr);
        System.out.println("processorAdditionalPrice : " + processorAdditionalPrice);
        String ramFullStr = computerEssentialComp.selectRamType(computerData.getRam());
        double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);
        System.out.println("RamAdditionalPrice " + ramAdditionalPrice);
        String hddFullStr = computerEssentialComp.selectHDD(computerData.getHdd());
        double additionalHddPrice = extractAdditionalPrice(hddFullStr);
        System.out.println("addition Hdd price: " + additionalHddPrice);

        double additionalOsPrice = 0;
        if (computerData.getOs() != null) {

            String fullOsStr = computerEssentialComp.selectOs(computerData.getOs());
            additionalOsPrice = extractAdditionalPrice(fullOsStr);

        }

        System.out.println("Os price" + additionalOsPrice);
        String fullSoftwareStr = computerEssentialComp.selectSoftware(computerData.getSoftware());
        double additionalSoftwarePrice = extractAdditionalPrice(fullSoftwareStr);

        //calculata items price and add to card total

        double basePrice = computerEssentialComp.getProductPrice();
        double allAdditionalPrices = processorAdditionalPrice + ramAdditionalPrice + additionalHddPrice + additionalOsPrice + additionalSoftwarePrice;
        double totalItemPrice = (basePrice + allAdditionalPrices) * quantity;
        //Add to cart
        computerEssentialComp.clickOnAddToCartBtn();
        computerEssentialComp.waitUntilItemAddToCart();


//        System.out.println(processorAdditionalPrice);
//        System.out.println(ramAdditionalPrice);
//        System.out.println(additionalHddPrice);
//        System.out.println(additionalOsPrice);
//        System.out.println(additionalSoftwarePrice);
//        System.out.println(basePrice);
//        System.out.println("totalItemPrice: " + totalItemPrice);
//        try{
//            Thread.sleep(3000);
//        }catch(Exception e){}
        //Then navigate to shopping cart
        computerItemDetailsPage.getHeaderComponent().clickOnShoppingCartLink();



    }

    private double extractAdditionalPrice(String itemStr) {
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemStr);
        if (matcher.find()) {
            price = Double.parseDouble(matcher.group(1).replaceAll("[-+]", ""));
        }
        return price;
    }

    public void verifyShoppingCartPage(){

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        TotalComponent totalComp = shoppingCartPage.getTotalComp();
        Map<String,Double> priceCategories = totalComp.priceCategories();
        for (String priceType : priceCategories.keySet()) {

            System.out.println("priceType" + priceType + " " + priceCategories.get(priceType));


        }

    }
}
