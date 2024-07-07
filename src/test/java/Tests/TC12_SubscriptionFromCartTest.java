package Tests;

import Pages.P01_HomePage;
import Pages.P10_CartPage;
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
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC12_SubscriptionFromCartTest {
    private final By AdsCloseButton= By.id("dismiss-button");
    String AdsUrl="https://www.automationexercise.com/#google_vignette";
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
            4. Click 'Cart' button and scroll down to footer
            5. Verify text 'SUBSCRIPTION'
            6. Enter email address in input and click arrow button
            7. Verify success message 'You have been successfully subscribed!' is visible""")
    public void verifySubscriptionFromCartTC() throws IOException {
        new P01_HomePage(getDriver()).ClickOnCartButton();
        Assert.assertTrue(new P10_CartPage(getDriver()).VerifyURLAssertion(getDriver(),DataUtils.getPropertyValue("environment", "CART_PAGE_URL")));
        LogsUtils.info("Now We are in Cart Page");
        new P10_CartPage(getDriver()).ScrollingInCartPage().SendSubscriptionEmail(DataUtils.getJsonData("Subscription","Email")).ClickOnSubscriptionButton();
        Assert.assertTrue(new P10_CartPage(getDriver()).VerifyThatWeSubscribeCorrectly("You have been successfully subscribed!"));
    }


    @AfterMethod(alwaysRun = true)
    public void quit() {}
}
