package Tests;

import Pages.P01_HomePage;
import Pages.P08_ProductPage;
import Utilities.LogsUtils;
import jdk.jfr.Description;
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
public class TC19_ViewCategoryProductsTest {
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
            3. Verify that categories are visible on left side bar
            4. Click on 'Women' category
            5. Click on any category link under 'Women' category, for example: Dress
            6. Verify that category page is displayed and confirm text 'WOMEN - DRESS PRODUCTS'
            7. On left side bar, click on any sub-category link of 'Men' category
            8. Verify that user is navigated to that category page""")
    public void VerifyViewCategoryProductsTC() throws IOException {

        Assert.assertTrue(new P01_HomePage(getDriver()).VerifyThatTheCategoryIsVisible("CATEGORY"));
        LogsUtils.info("The Category Is Visible ");
        new P01_HomePage(getDriver()).ClickInDressButtonInWomenCategory();
        Assert.assertTrue(new P08_ProductPage(getDriver()).VerifyWomanDressCategory("WOMEN - DRESS PRODUCTS"));
        LogsUtils.info("The Dress In Women Category Is Visible ");
        new P08_ProductPage(getDriver()).ClickInTshirtButtonInMenCategory();
        Assert.assertTrue(new P08_ProductPage(getDriver()).VerifyMenTshirtCategory("MEN - TSHIRTS PRODUCTS"));
        LogsUtils.info("The Tshirt In Men Category Is Visible ");

    }


    @AfterMethod(alwaysRun = true)
    public void quit() {}
}
