package Tests;

import Pages.P01_HomePage;
import Pages.P08_ProductPage;
import Pages.P09_ProductDetailsPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import jdk.jfr.Description;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setupDriver;
import static Utilities.DataUtils.getPropertyValue;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC10_SearchProductTest {
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
            4. Click on 'Products' button
            5. Verify user is navigated to ALL PRODUCTS page successfully
            6. Enter product name in search input and click search button
            7. Verify 'SEARCHED PRODUCTS' is visible
            8. Verify all the products related to search are visible""")
    public void verifySearchProductTC() throws IOException {
        new P01_HomePage(getDriver()).ClickOnProductButton();

        if(getDriver().getCurrentUrl().equals(AdsUrl))
        {
            getDriver().get("https://www.automationexercise.com/products");
        }
        Assert.assertTrue(new P08_ProductPage(getDriver()).VerifyThatAllProductIsVisible("ALL PRODUCTS"));
        LogsUtils.info("Now We are in Product Page And all Product is Visible");
        new P08_ProductPage(getDriver()).InputSearchText(DataUtils.getJsonData("Search","SearchProduct")).ClickInSearchButton();
        Assert.assertTrue(new P08_ProductPage(getDriver()).VerifyThatSearchedProductsIsVisible("SEARCHED PRODUCTS"));
        LogsUtils.info("SEARCHED PRODUCTS is Visible");
        List<WebElement> NameOfProducts=new P08_ProductPage(getDriver()).AllProductNameAfterSearch();
        for(int i=0;i< NameOfProducts.size();i++)
        {
            Assert.assertTrue(NameOfProducts.get(i).getText().toLowerCase().contains(DataUtils.getJsonData("Search","SearchProduct").toLowerCase()));
            LogsUtils.info("the Product "+i+" has name "+NameOfProducts.get(i).getText().toLowerCase());
        }
    }


    @AfterMethod(alwaysRun = true)
    public void quit() {}
}
