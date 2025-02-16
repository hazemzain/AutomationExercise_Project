package Tests;

import Pages.P01_HomePage;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;
@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC01_HomeTest {
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
    public void CheckSignupAndLoginButtonsTC() throws IOException {
        new P01_HomePage(getDriver()).ClickOnSignupLoginButton();
        Assert.assertTrue(new P01_HomePage(getDriver()).VerifyURLAssertion(getDriver(),getPropertyValue("environment","LOGIN_PAGE_URL")));
    }


    @AfterMethod(alwaysRun = true)
    public void quit() {}
    }

