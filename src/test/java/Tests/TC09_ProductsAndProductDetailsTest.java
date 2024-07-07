package Tests;

import Pages.*;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.openqa.selenium.Alert;
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
public class TC09_ProductsAndProductDetailsTest {
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
    public void verifyAllProductsAndProductDetailPageTC() throws IOException {
        new P01_HomePage(getDriver()).ClickOnProductButton();
        if(getDriver().getCurrentUrl().equals(AdsUrl))
        {
            getDriver().get("https://www.automationexercise.com/products");
        }
        Assert.assertTrue(new P08_ProductPage(getDriver()).VerifyThatAllProductIsVisible("ALL PRODUCTS"));
        LogsUtils.info("Now We are in Product Page And all Product is Visible");
        new P08_ProductPage(getDriver()).ClickOnViewButtonForFirstProduct();
        Assert.assertTrue(new P09_ProductDetailsPage(getDriver()).verifyThatNameOfProductIsVisible());
        LogsUtils.info("the name of Product is Visible");
        Assert.assertTrue(new P09_ProductDetailsPage(getDriver()).verifyThatCategoryOfProductIsVisible());
        LogsUtils.info("the Category of Product is Visible");
        Assert.assertTrue(new P09_ProductDetailsPage(getDriver()).verifyThatPriceOfProductLocatorIsVisible());
        LogsUtils.info("the price of Product is Visible");

    }


    @AfterMethod(alwaysRun = true)
    public void quit() {}
}
