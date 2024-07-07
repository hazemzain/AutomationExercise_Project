package Tests;

import Pages.P01_HomePage;
import Pages.P06_ContactUsPage;
import Pages.P07_FeedBackContactUsPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.openqa.selenium.Alert;
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
public class TC08_ContactUsTest {
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
    public void VerifyContactUsFormTC() throws IOException {
        new P01_HomePage(getDriver()).ClickContactUsButton();
        Assert.assertTrue(new P01_HomePage(getDriver()).VerifyURLAssertion(getDriver(),getPropertyValue("environment","CONTACTUS_PAGE_URL")));
        LogsUtils.info("Now We are in Contact Us Page");
        new P06_ContactUsPage(getDriver()).FullInformationOfContactUsForm(
                DataUtils.getJsonData("ContactUs","UserName"),
                DataUtils.getJsonData("ContactUs","UserEmail"),
                DataUtils.getJsonData("ContactUs","Subject"),
                DataUtils.getJsonData("ContactUs","Message")).ClickSubmitButton();
        Alert alert=getDriver().switchTo().alert();
        alert.accept();
        Assert.assertTrue(new P07_FeedBackContactUsPage(getDriver()).VerifyThatContactInformationIsSubmittedCorrectly("Success! Your details have been submitted successfully."));
        LogsUtils.info("The ContactUs Form is Submitted Correctly ");
        new P07_FeedBackContactUsPage(getDriver()).ClickOnGoHomeButton();
        Assert.assertTrue(new P01_HomePage(getDriver()).VerifyURLAssertion(getDriver(),getPropertyValue("environment","HOME_URL")));
        LogsUtils.info("After Submittion we Go Back Correctly to Home Page ");
    }


    @AfterMethod(alwaysRun = true)
    public void quit() {}
}
