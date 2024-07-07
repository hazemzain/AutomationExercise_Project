package Pages;

import Utilities.DataUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02_LoginPage {
    private final WebDriver driver;
    private final By SignupWithNameLocator= By.cssSelector("input[data-qa='signup-name']");
    private final By SignupWithEmailLocator=By.cssSelector("input[data-qa='signup-email']");
    private final By SignUpButtonLocator=By.xpath("//form/button[@data-qa='signup-button']");
    private final By SignInWithEmailLocator=By.xpath("//input[@type='email']");
    private final By SignInWithPasswordLocator=By.xpath("//input[@type='password']");
    private final By SignInButtonLocator=By.xpath("//button[@data-qa='login-button']");
    private final By RegisterWithExistAccountLocator=By.xpath("//form/p[@style]");
    public P02_LoginPage(WebDriver driver) {
        this.driver=driver;
    }
    public P02_LoginPage RegisterNewUser(String UserName,String UserEmail)
    {
        Utility.sendData(driver,SignupWithNameLocator,UserName);
        Utility.sendData(driver,SignupWithEmailLocator,UserEmail);

        return this;
    }
    public P02_LoginPage RegisterNewUserWithExistAccount(String UserName,String UserEmail)
    {
        Utility.sendData(driver,SignupWithNameLocator,UserName);
        Utility.sendData(driver,SignupWithEmailLocator,UserEmail);

        return this;
    }
    //Email Address already exist!
    public Boolean VerifyRegisterWithExistAccount(String ExpectedMessage)
    {
        return Utility.getText(driver,RegisterWithExistAccountLocator).equals(ExpectedMessage);
    }
    public P03_RegisterPage ClickOnSignupButton()
    {
        Utility.clickingOnElement(driver,SignUpButtonLocator);
        return new P03_RegisterPage(driver);
    }
    public Boolean VerifyURLAssertion(WebDriver driver,String expectedURL)
    {
        return driver.getCurrentUrl().equals(expectedURL);
    }
    public P02_LoginPage SignInForUser(String UserEmail,String UserPassword)
    {
        Utility.sendData(driver,SignInWithEmailLocator,UserEmail);
        Utility.sendData(driver,SignInWithPasswordLocator,UserPassword);

        return this;
    }
    public P01_HomePage ClickOnSignInButton()
    {
        Utility.clickingOnElement(driver,SignInButtonLocator);
        return new P01_HomePage(driver);
    }
}
