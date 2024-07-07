package Tests;

import Pages.*;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setupDriver;
import static Utilities.DataUtils.getPropertyValue;
import static Utilities.Utility.getTimeStamp;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC17_PlaceOrderLoginBeforeCheckOutTest {
    private final By AdsCloseButton= By.id("dismiss-button");
    String AdsUrl="https://www.automationexercise.com/#google_vignette";
    String UserEmail= DataUtils.getJsonData("ValidLogin","UserEmail") ;

    String UserPassword= DataUtils.getJsonData("ValidLogin","password");
    @BeforeMethod(alwaysRun = true)
    public void setup() throws IOException {
        //condition ? true : false
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : getPropertyValue("environment", "Browser");
        LogsUtils.info(System.getProperty("browser"));
        setupDriver(browser);
        LogsUtils.info(browser + " driver is opened");
        getDriver().get(getPropertyValue("environment", "HOME_URL"));
        LogsUtils.info("Page is redirected to the Home URL");
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click 'Signup / Login' button
            5. Fill email, password and click 'Login' button
            6. Verify 'Logged in as username' at top
            7. Add products to cart
            8. Click 'Cart' button
            9. Verify that cart page is displayed
            10. Click Proceed To Checkout
            11. Verify Address Details and Review Your Order
            12. Enter description in comment text area and click 'Place Order'
            13. Enter payment details: Name on Card, Card Number, CVC, Expiration date
            14. Click 'Pay and Confirm Order' button
            15. Verify success message 'Congratulations! Your order has been confirmed!'""")

    public void verifyPlaceOrderLoginBeforeCheckOutTC() throws IOException {

        new P01_HomePage(getDriver()).ClickOnSignupLoginButton();
        Assert.assertTrue(new P01_HomePage(getDriver()).VerifyURLAssertion(getDriver(),getPropertyValue("environment","LOGIN_PAGE_URL")));
        LogsUtils.info("Now We are in Login Page");
        new P01_HomePage(getDriver()).ClickOnSignupLoginButton().SignInForUser(UserEmail,UserPassword).ClickOnSignInButton();
        Assert.assertTrue(new P01_HomePage(getDriver()).VerifyThatWeSignInCorrectly(DataUtils.getJsonData("ValidLogin","UserName")));

        new P01_HomePage(getDriver()).ClickOnProductButton();
        if(getDriver().getCurrentUrl().equals(AdsUrl))
        {
            getDriver().get("https://www.automationexercise.com/products");
        }
        Assert.assertTrue(new P08_ProductPage(getDriver()).VerifyThatAllProductIsVisible("ALL PRODUCTS"));
        LogsUtils.info("Now We are in Product Page And all Product is Visible");
        new P08_ProductPage(getDriver())
                .AddAllProductToCart();
        new P01_HomePage(getDriver()).ClickOnCartButton();
        Assert.assertTrue(new P10_CartPage(getDriver()).VerifyURLAssertion(getDriver(),DataUtils.getPropertyValue("environment", "CART_PAGE_URL")));
        LogsUtils.info("Now We are in Cart Page");
        new P10_CartPage(getDriver()).ClickOnProceedToCheckOutButton().ClickOnPlaceOrderButton();
        Assert.assertTrue(new P11_PaymentPage(getDriver()).VerifyURLAssertion(getDriver(),getPropertyValue("environment","PAYMENT_PAGE_URL")));
        LogsUtils.info("Now We are in Payment Page");
        new P11_PaymentPage(getDriver()).FullPaymentInformationForOrder(
                DataUtils.getJsonData("PaymentInformation","NameOnCard"),
                DataUtils.getJsonData("PaymentInformation","CardNumber"),
                DataUtils.getJsonData("PaymentInformation","Cvc"),
                DataUtils.getJsonData("PaymentInformation","ExpirationMonth"),
                DataUtils.getJsonData("PaymentInformation","ExpirationYear")
        ).ClickOnPayAndConfirmOrderButton();
        Assert.assertTrue(new P12_PaymentDonePage(getDriver()).VerifyThatTheOrderPlace("Congratulations! Your order has been confirmed!"));
        LogsUtils.info("Now We are in PaymentDone Page");


    }


    @AfterMethod(alwaysRun = true)
    public void quit() {}
}
