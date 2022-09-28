package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.BillingAddressComponent;
import models.components.checkout.ShippingMethodComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.CheckoutOptionsPage;
import models.pages.CheckOutPage;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.DataObjectBuilder;
import test_data.computer.ComputerData;
import test_data.user.UserDataObject;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private ComputerData computerData;
    private final int quantity;
    private double totalItemPrice;

    private UserDataObject defaultCheckoutUser;

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
        List<CartItemRowComponent> cartItemRowComp = shoppingCartPage.cartItemRowComponentList();

        if(cartItemRowComp.isEmpty())
            Assert.fail("[ERR]  This is no item displayed in the shopping cart!");


        double currentSubtotal = 0;
        double currentTotalUnitPrices = 0;

        for (CartItemRowComponent cartItemRowComponent : cartItemRowComp) {

            currentSubtotal += cartItemRowComponent.getsubTotal();
            currentTotalUnitPrices+= cartItemRowComponent.getQuantity()* cartItemRowComponent.getUnitPrice();

        }

        Assert.assertEquals(currentSubtotal,currentTotalUnitPrices, "[ERR] Shopping cart's sub-total is correct");
        TotalComponent totalComp = shoppingCartPage.getTotalComp();
        Map<String,Double> priceCategories = totalComp.priceCategories();

        double checkoutSubtotal = 0;
        double checkoutOtherFeesTotal = 0;
        double checkoutTotal = 0;

        for (String priceType : priceCategories.keySet()) {

            double priceValue = priceCategories.get(priceType);
            if(priceType.startsWith("Sub-Total")){

                checkoutSubtotal = priceValue;
            } else if (priceType.startsWith("Total")) {
                checkoutTotal = priceValue;
            }else {
                checkoutOtherFeesTotal += priceValue;
            }

        }

        Assert.assertEquals(checkoutSubtotal, currentSubtotal,"[ERR] ...");
        Assert.assertEquals(checkoutTotal, currentSubtotal + checkoutOtherFeesTotal,"[ERR] ...");


    }


    public void agreeTOSAndCheckout(){

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.getTotalComp().agreeTOS();
        shoppingCartPage.getTotalComp().clickOnCheckoutBtn();
        new CheckoutOptionsPage(driver).checkoutAsGuest();

    }

    public void inputBillingAddress(){

        String defaultCheckoutUserJSONLoc = "src/test/java/test_data/DefaultCheckoutUser.json";
        defaultCheckoutUser = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserJSONLoc,UserDataObject.class);
        CheckOutPage checkoutPage = new CheckOutPage(driver);
        BillingAddressComponent billingAddressComp = checkoutPage.billingAddressComponent();
        billingAddressComp.selectInputNewAddress();
        billingAddressComp.inputFirstname(defaultCheckoutUser.getFirstName());
        billingAddressComp.inputLastname(defaultCheckoutUser.getLastName());
        billingAddressComp.inputEmail(defaultCheckoutUser.getEmail());
        billingAddressComp.selectCountry(defaultCheckoutUser.getCountry());
        billingAddressComp.selectState(defaultCheckoutUser.getState());
        billingAddressComp.inputCity(defaultCheckoutUser.getCity());
        billingAddressComp.inputAdd1(defaultCheckoutUser.getAdd1());
        billingAddressComp.inputZIPCode(defaultCheckoutUser.getZipCode());
        billingAddressComp.inputPhoneNo(defaultCheckoutUser.getPhoneNum());
        billingAddressComp.clickOnContinueBtn();


    }

    public void inputShippingAddress(){
        CheckOutPage checkoutPage = new CheckOutPage(driver);
        checkoutPage.shippingAddressComponent().clickOnContinueBtn();
    }

    public void selectPaymentMethod(){

        List<String> shippingMethods = Arrays.asList("Ground", "Next Day Air", "2nd Day Air");
        String randomShippingMethod = shippingMethods.get(new SecureRandom().nextInt(shippingMethods.size()));

        CheckOutPage checkOutPage = new CheckOutPage(driver);

        ShippingMethodComponent shippingMethodComp = checkOutPage.shippingMethodComponent()
                ;
        shippingMethodComp.selectShippingMethod(randomShippingMethod).clickOnContinueButton();

        try {
            Thread.sleep(4000);
        }catch (Exception ignored){}

    }

    public void inputPaymentInfo(){


    }

    public void confirmOrder(){


    }
}
