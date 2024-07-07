package Tests;

import Pages.P01_HomePage;
import Pages.P02_LoginPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
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
public class TC07_RegisterWithExistEmailTest {
    String UserEmail= DataUtils.getJsonData("ValidLogin","UserEmail") ;

    String UserName= DataUtils.getJsonData("ValidLogin","UserName");
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
    public void VerifyRegisterWithExistEmailTC() throws IOException {
        new P01_HomePage(getDriver()).ClickOnSignupLoginButton().RegisterNewUserWithExistAccount(UserName,UserEmail).ClickOnSignupButton();
        Assert.assertTrue(new P02_LoginPage(getDriver()).VerifyRegisterWithExistAccount("Email Address already exist!"));
        LogsUtils.info("You SignUp with  Exist Email ");
    }


    @AfterMethod(alwaysRun = true)
    public void quit() {}
}