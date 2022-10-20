package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.BillingAddressComponent;
import models.components.checkout.PaymentInformationComponent;
import models.components.checkout.PaymentMethodComponent;
import models.components.checkout.ShippingMethodComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.CheckOutPage;
import models.pages.CheckoutOptionsPage;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.CreditCardType;
import test_data.DataObjectBuilder;
import test_data.PaymentMethod;
import test_data.computer.ComputerData;
import test_data.user.UserDataObject;

import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private ComputerData computerData;
    private final int quantity;
    private double totalItemPrice;

    private UserDataObject defaultCheckoutUser;
    private PaymentMethod paymentMethod;
    private CreditCardType creditCardType;

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

    public void buildCompSpecAndAddToCart() {


        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.getComputerComp(computerEssentialComponent);
        // Unselect all default option
        computerEssentialComp.unselectDefaultOption();
        String processorFullStr = computerEssentialComp.selectProcessorType(computerData.getProcessorType());

        double processorAdditionalPrice = extractAdditionalPrice(processorFullStr);

        String ramFullStr = computerEssentialComp.selectRamType(computerData.getRam());

        double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);

        String hddFullStr = computerEssentialComp.selectHDD(computerData.getHdd());
        double additionalHddPrice = extractAdditionalPrice(hddFullStr);

        double additionalOsPrice = 0;
        if (computerData.getOs() != null) {

            String fullOsStr = computerEssentialComp.selectOs(computerData.getOs());
            additionalOsPrice = extractAdditionalPrice(fullOsStr);

        }


        String fullSoftwareStr = computerEssentialComp.selectSoftware(computerData.getSoftware());
        double additionalSoftwarePrice = extractAdditionalPrice(fullSoftwareStr);

        //calculater items price and add to card total

        double basePrice = computerEssentialComp.getProductPrice();
        double allAdditionalPrices = processorAdditionalPrice + ramAdditionalPrice + additionalHddPrice + additionalOsPrice + additionalSoftwarePrice;
        double totalItemPrice = (basePrice + allAdditionalPrices) * quantity;
        //Add to cart
        computerEssentialComp.clickOnAddToCartBtn();
        computerEssentialComp.waitUntilItemAddToCart();

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

    public void verifyShoppingCartPage() {

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<CartItemRowComponent> cartItemRowComp = shoppingCartPage.cartItemRowComponentList();

        if (cartItemRowComp.isEmpty())
            Assert.fail("[ERR]  This is no item displayed in the shopping cart!");


        double currentSubtotal = 0;
        double currentTotalUnitPrices = 0;

        for (CartItemRowComponent cartItemRowComponent : cartItemRowComp) {

            currentSubtotal += cartItemRowComponent.getsubTotal();
            currentTotalUnitPrices += cartItemRowComponent.getQuantity() * cartItemRowComponent.getUnitPrice();

        }

        Assert.assertEquals(currentSubtotal, currentTotalUnitPrices, "[ERR] Shopping cart's sub-total is correct");
        TotalComponent totalComp = shoppingCartPage.getTotalComp();
        Map<String, Double> priceCategories = totalComp.priceCategories();

        double checkoutSubtotal = 0;
        double checkoutOtherFeesTotal = 0;
        double checkoutTotal = 0;

        for (String priceType : priceCategories.keySet()) {

            double priceValue = priceCategories.get(priceType);
            if (priceType.startsWith("Sub-Total")) {

                checkoutSubtotal = priceValue;
            } else if (priceType.startsWith("Total")) {
                checkoutTotal = priceValue;
            } else {
                checkoutOtherFeesTotal += priceValue;
            }

        }

        Assert.assertEquals(checkoutSubtotal, currentSubtotal, "[ERR] ...");
        Assert.assertEquals(checkoutTotal, currentSubtotal + checkoutOtherFeesTotal, "[ERR] ...");


    }


    public void agreeTOSAndCheckout() {

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.getTotalComp().agreeTOS();
        shoppingCartPage.getTotalComp().clickOnCheckoutBtn();
        new CheckoutOptionsPage(driver).checkoutAsGuest();

    }

    public void inputBillingAddress() {

        String defaultCheckoutUserJSONLoc = "/src/main/java/test_data/DefaultCheckoutUser.json";


        defaultCheckoutUser = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserJSONLoc, UserDataObject.class);


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

    public void inputShippingAddress() {
        CheckOutPage checkoutPage = new CheckOutPage(driver);
        checkoutPage.shippingAddressComponent().clickOnContinueBtn();
    }

    public void selectShippingMethod() {

        List<String> shippingMethods = Arrays.asList("Ground", "Next Day Air", "2nd Day Air");
        String randomShippingMethod = shippingMethods.get(new SecureRandom().nextInt(shippingMethods.size()));

        CheckOutPage checkOutPage = new CheckOutPage(driver);

        ShippingMethodComponent shippingMethodComp = checkOutPage.shippingMethodComponent();
        shippingMethodComp.selectShippingMethod(randomShippingMethod).clickOnContinueButton();

        try {
            Thread.sleep(4000);
        } catch (Exception ignored) {
        }

    }

    public void selectPaymentMethod() {

        this.paymentMethod = PaymentMethod.COD;
    }

    public void selectPaymentMethod(PaymentMethod paymentMethod) {

        if (paymentMethod == null) {

            throw new IllegalArgumentException("[ERR] payment method cannot be null");

        }

        this.paymentMethod = paymentMethod;
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        PaymentMethodComponent paymentMethodComp = checkOutPage.paymentMethodComponent();
        switch (paymentMethod) {
            case CHECK_MONEY_ORDER:
                paymentMethodComp.selectCheckMoneyOrderMethod();
                break;
            case CREDIT_CARD:
                paymentMethodComp.selectCreditCardMethod();
                break;
            case PURCHASE_ORDER:
                paymentMethodComp.selectPurchaseOrderMethod();
                break;
            default:
                paymentMethodComp.selectCODMethod();


        }


        paymentMethodComp.clickOnContinueBtn();


    }

    public void inputPaymentInfo(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        PaymentInformationComponent paymentInformationComp = checkOutPage.paymentInformationComponent();

        if (this.paymentMethod.equals(PaymentMethod.PURCHASE_ORDER)) {
            // This can be dynamic as well
            paymentInformationComp.inputPurchaseNum("123");
        } else if (this.paymentMethod.equals(PaymentMethod.CREDIT_CARD)) {
            paymentInformationComp.selectCardType(creditCardType);
            String cardHolderFirstName = this.defaultCheckoutUser.getFirstName();
            String cardHolderLastName = this.defaultCheckoutUser.getLastName();
            paymentInformationComp.inputCardHolderName(cardHolderFirstName + " " + cardHolderLastName);
            //4012888888881881": Visa
            // : "6011000990139424: Discover

            String cardNumber = creditCardType.equals(CreditCardType.VISA) ? "4012888888881881" : "6011000990139424";
            paymentInformationComp.inputCardNumber(cardNumber);

            // Select current month and next year
            Calendar calendar = new GregorianCalendar();
            paymentInformationComp.inputExpiredMonth(String.valueOf(calendar.get(Calendar.MONTH) + 1));
            paymentInformationComp.inputExpiredYear(String.valueOf(calendar.get(Calendar.YEAR) + 1));
            paymentInformationComp.inputCardCode("123");
            paymentInformationComp.clickOnContinueBtn();
        } else if (this.paymentMethod.equals(PaymentMethod.COD)) {
            // TODO: add verification
        } else {
            // TODO: Verify cheque...
        }
    }

    public void confirmOrder() {
// TODO: add verification method
        new CheckOutPage(driver).confirmOrderComponent().clickOnContinueBtn();

        try{

            Thread.sleep(3000);

        }catch(Exception e){}

    }
}
