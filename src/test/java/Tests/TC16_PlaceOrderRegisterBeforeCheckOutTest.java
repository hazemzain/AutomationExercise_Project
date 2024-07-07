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
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setupDriver;
import static Utilities.DataUtils.getPropertyValue;
import static Utilities.Utility.getTimeStamp;
@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC16_PlaceOrderRegisterBeforeCheckOutTest {
    private final By AdsCloseButton= By.id("dismiss-button");
    String AdsUrl="https://www.automationexercise.com/#google_vignette";
    String UserName= DataUtils.getJsonData("RegisterNewUser","UserName")+getTimeStamp() ;

    String UserEmail= DataUtils.getJsonData("RegisterNewUser","Email")+getTimeStamp() + "@gmail.com";
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
            5. Fill all details in Signup and create account
            6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
            7. Verify ' Logged in as username' at top
            8. Add products to cart
            9. Click 'Cart' button
            10. Verify that cart page is displayed
            11. Click Proceed To Checkout
            12. Verify Address Details and Review Your Order
            13. Enter description in comment text area and click 'Place Order'
            14. Enter payment details: Name on Card, Card Number, CVC, Expiration date
            15. Click 'Pay and Confirm Order' button
            16. Verify success message 'Congratulations! Your order has been confirmed!'
            17. Click 'Delete Account' button
            18. Verify that 'ACCOUNT DELETED!' and click 'Continue' button""")

    public void verifyPlaceOrderRegisterBeforeCheckOutTC() throws IOException {
        LogsUtils.info("username is = "+UserName);
        LogsUtils.info("username is = "+UserEmail);
        new P01_HomePage(getDriver()).ClickOnSignupLoginButton();
        Assert.assertTrue(new P01_HomePage(getDriver()).VerifyURLAssertion(getDriver(),getPropertyValue("environment","LOGIN_PAGE_URL")));
        LogsUtils.info("Now We are in Login Page");
        new P02_LoginPage(getDriver()).RegisterNewUser(UserName,UserEmail).ClickOnSignupButton().FullRegistrationInformationForNewUser(
                DataUtils.getJsonData("RegisterNewUser","Password"),
                DataUtils.getJsonData("RegisterNewUser","Days"),
                DataUtils.getJsonData("RegisterNewUser","Month"),
                DataUtils.getJsonData("RegisterNewUser","Years"),
                DataUtils.getJsonData("RegisterNewUser","FirstName"),
                DataUtils.getJsonData("RegisterNewUser","LastName"),
                DataUtils.getJsonData("RegisterNewUser","Company"),
                DataUtils.getJsonData("RegisterNewUser","Adress"),
                DataUtils.getJsonData("RegisterNewUser","Country"),
                DataUtils.getJsonData("RegisterNewUser","State"),
                DataUtils.getJsonData("RegisterNewUser","City"),
                DataUtils.getJsonData("RegisterNewUser","ZipCode"),
                DataUtils.getJsonData("RegisterNewUser","MobileNumber")

        ).ClickOnCreatingAccountButton();
        Assert.assertTrue(new P03_RegisterPage(getDriver()).VerifyCreatedAccount(getDriver(),"ACCOUNT CREATED!"));
        new P04_AccountCreatedPage(getDriver()).ClickOnContinueButton();
        Assert.assertTrue(new P01_HomePage(getDriver()).VerifyThatWeSignInCorrectly(UserName));
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
        new P01_HomePage(getDriver()).ClickOnDeleteAccountButton();
        Assert.assertTrue(new P05_DeletePage(getDriver()).VerifyCreatedAccount(getDriver(),"ACCOUNT DELETED!"));
        LogsUtils.info("The Account Is Deleted Sucessfully");

    }


    @AfterMethod(alwaysRun = true)
    public void quit() {}
}
