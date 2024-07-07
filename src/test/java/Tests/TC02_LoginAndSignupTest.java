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
import static Utilities.Utility.getTimeStamp;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC02_LoginAndSignupTest {
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
    public void SignupTC() throws IOException {
        LogsUtils.info("username is = "+UserName);
        LogsUtils.info("username is = "+UserEmail);
        new P01_HomePage(getDriver()).ClickOnSignupLoginButton().RegisterNewUser(UserName,UserEmail).ClickOnSignupButton();
        Assert.assertTrue(new P02_LoginPage(getDriver()).VerifyURLAssertion(getDriver(),getPropertyValue("environment","SIGNUP_PAGE_URL")));
    }


    @AfterMethod(alwaysRun = true)
    public void quit() {}
}
