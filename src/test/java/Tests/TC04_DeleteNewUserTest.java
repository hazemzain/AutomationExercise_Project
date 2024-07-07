package Tests;

import Pages.P01_HomePage;
import Pages.P03_RegisterPage;
import Pages.P04_AccountCreatedPage;
import Pages.P05_DeletePage;
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
public class TC04_DeleteNewUserTest {
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
    public void VerifyDeletingAccountTC() throws IOException {
        LogsUtils.info("username is = "+UserName);
        LogsUtils.info("username is = "+UserEmail);
        new P01_HomePage(getDriver()).ClickOnSignupLoginButton().RegisterNewUser(UserName,UserEmail).ClickOnSignupButton().FullRegistrationInformationForNewUser(
                DataUtils.getJsonData("RegisterNewUser","Password"),
                DataUtils.getJsonData("RegisterNewUser","Days"),
                DataUtils.getJsonData("RegisterNewUser","Month"),
                DataUtils.getJsonData("RegisterNewUser","Years"),
                DataUtils.getJsonData("RegisterNewUser","FirstName"),
                DataUtils.getJsonData("RegisterNewUser","LastName"),
                DataUtils.getJsonData("RegisterNewUser","Company"),
                DataUtils.getJsonData("RegisterNewUser","Adress"),
                DataUtils.getJsonData("RegisterNewUser","Country"),
                DataUtils.getJsonData("RegisterNewUser","State"),
                DataUtils.getJsonData("RegisterNewUser","City"),
                DataUtils.getJsonData("RegisterNewUser","ZipCode"),
                DataUtils.getJsonData("RegisterNewUser","MobileNumber")

        ).ClickOnCreatingAccountButton();
        Assert.assertTrue(new P03_RegisterPage(getDriver()).VerifyCreatedAccount(getDriver(),"ACCOUNT CREATED!"));
        LogsUtils.info("The New Account Created Successfullly");
        new P04_AccountCreatedPage(getDriver()).ClickOnContinueButton();
        Assert.assertTrue(new P01_HomePage(getDriver()).VerifyThatWeSignWithTheSameUserName(UserName));
        LogsUtils.info("We Login With The Same Account We Created");
        /*
        * we want to delete account
        * 1- i will click in delete account button
        * 2-we will go to delete page and verify that the account is deleted
        * 3-we will click in continue button and we will go to Home page
        * */
        //
         new P01_HomePage(getDriver()).ClickOnDeleteAccountButton();
        Assert.assertTrue(new P05_DeletePage(getDriver()).VerifyCreatedAccount(getDriver(),"ACCOUNT DELETED!"));
        LogsUtils.info("The Account Is Deleted Sucessfully");
    }


    @AfterMethod(alwaysRun = true)
    public void quit() {}
}
