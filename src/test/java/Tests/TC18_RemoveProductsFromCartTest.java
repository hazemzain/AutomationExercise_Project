package Tests;

import Pages.P01_HomePage;
import Pages.P08_ProductPage;
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
public class TC18_RemoveProductsFromCartTest {
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
            4. Add products to cart
            5. Click 'Cart' button
            6. Verify that cart page is displayed
            7. Click 'X' button corresponding to particular product
            8. Verify that product is removed from the cart""")
    public void verifyRemoveProductsFromCartTC() throws IOException {
        new P01_HomePage(getDriver()).ClickOnProductButton();
        if(getDriver().getCurrentUrl().equals(AdsUrl))
        {
            getDriver().get("https://www.automationexercise.com/products");
        }
        Assert.assertTrue(new P08_ProductPage(getDriver()).VerifyThatAllProductIsVisible("ALL PRODUCTS"));
        LogsUtils.info("Now We are in Product Page And all Product is Visible");
        new P08_ProductPage(getDriver()).AddProductOneToCart().ClickInContinueButton().AddProductTwoToCart().ClickOnViewCartButton();
        Assert.assertTrue(new P10_CartPage(getDriver()).VerifyURLAssertion(getDriver(), DataUtils.getPropertyValue("environment", "CART_PAGE_URL")));
        LogsUtils.info("Now We are in Cart View Page");
      new P10_CartPage(getDriver()).RemoveAllProductInTheCart();
      Assert.assertTrue(new P10_CartPage(getDriver()).VerifyThatTheCartIsEmpty("Cart is empty!"));

    }


    @AfterMethod(alwaysRun = true)
    public void quit() {}
}
