package Tests;

import Pages.P01_HomePage;
import Pages.P08_ProductPage;
import Pages.P09_ProductDetailsPage;
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
public class TC21_AddReviewOnproductTest {
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
            3. Click on 'Products' button
            4. Verify user is navigated to ALL PRODUCTS page successfully
            5. Click on 'View Product' button
            6. Verify 'Write Your Review' is visible
            7. Enter name, email and review
            8. Click 'Submit' button
            9. Verify success message 'Thank you for your review.'""")
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
        new P09_ProductDetailsPage(getDriver()).EnterFullReviewDataForProduct(
                DataUtils.getJsonData("ReviewProduct","Name"),
                DataUtils.getJsonData("ReviewProduct","Email"),
                DataUtils.getJsonData("ReviewProduct","ReviewMessage")
        ).ClickSubmitReviewButton();
        Assert.assertTrue(new P09_ProductDetailsPage(getDriver()).VerifyThatReviewMessageSubmitCorrectly("Thank you for your review."));
        LogsUtils.info("The Review Message Submit correctly");

    }


    @AfterMethod(alwaysRun = true)
    public void quit() {}
}
