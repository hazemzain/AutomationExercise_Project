package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_HomePage {
    private final By Signup_Login_Button_Locator=By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[4]/a")/* By.linkText("/login")*/;
    private final By DeleteAccountButtonLocator=By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[5]/a");
    private final By SignAsLocator=By.xpath("//a/b");
    private final By LoginInAsLocator=By.xpath("//a/b");
    private final By SignOutButtonLocator=By.xpath("//div//li[4]/a");
    private final By ContactUsButtonLocator=By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[8]/a");
    private final By ProductButtonLocator=By.xpath("//li/a[@href='/products']");
    private final By SubscriptionMessageLocator=By.xpath("//div[@class='single-widget']/h2");
    private final By SubscriptionEmailLocator=By.id("susbscribe_email");
    private final By SubscriptionButtonLocator=By.id("subscribe");
    private final By alertSuccessSubscribeLocator=By.id("success-subscribe");
    private final By CartButtonLocator=By.xpath("//li/a[@href='/view_cart']");
    private final By CategoryLocator=By.xpath("//div[@class='left-sidebar']/h2");
    private final By CategoryWomenButtonLocator=By.xpath("//a[@href='#Women' ]/span/i");
    private final By DressInCategoryWomenButtonLocator=By.xpath("//a[@href='/category_products/1' ]");
            ////li/a[@href='/product_details/1']

    private final WebDriver driver;

    public P01_HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public P08_ProductPage ClickOnProductButton()
    {
        Utility.clickingOnElement(driver,ProductButtonLocator);
        return new P08_ProductPage(driver);
    }
    public P08_ProductPage ClickInDressButtonInWomenCategory()
    {
     Utility.clickingOnElement(driver,CategoryWomenButtonLocator);
     Utility.clickingOnElement(driver,DressInCategoryWomenButtonLocator);
        return new P08_ProductPage(driver);
    }
    public P02_LoginPage ClickOnSignOutButton()
    {
        Utility.clickingOnElement(driver,SignOutButtonLocator);
        return new P02_LoginPage(driver);
    }
    public P02_LoginPage ClickOnSignupLoginButton()
    {
        Utility.clickingOnElement(driver,Signup_Login_Button_Locator);
        return new P02_LoginPage(driver);
    }
    public P06_ContactUsPage ClickContactUsButton()
    {
        Utility.clickingOnElement(driver,ContactUsButtonLocator);
        return new P06_ContactUsPage(driver);
    }
    public Boolean VerifyURLAssertion(WebDriver driver,String expectedURL)
    {
        return driver.getCurrentUrl().equals(expectedURL);
    }
    public P05_DeletePage ClickOnDeleteAccountButton()
    {
        Utility.clickingOnElement(driver,DeleteAccountButtonLocator);
        return new P05_DeletePage(driver);
    }
    public Boolean VerifyThatWeSignWithTheSameUserName(String ExepectedUserName)
    {
        LogsUtils.info("the Actual UserName sign With is = "+Utility.getText(driver,SignAsLocator));
        LogsUtils.info("the Expecteds UserName sign With is = "+ExepectedUserName);
        return Utility.getText(driver,SignAsLocator).equals(ExepectedUserName);
    }
    public Boolean VerifyThatTheCategoryIsVisible(String ExpectedWord)
    {
        LogsUtils.info("the Actual   Word is = "+Utility.getText(driver,CategoryLocator));
        LogsUtils.info("the Expected Word is = "+ExpectedWord);
        return Utility.getText(driver,CategoryLocator).equals(ExpectedWord);
    }
    public Boolean VerifyThatWeSignInCorrectly(String ExepectedUserName)
    {
        LogsUtils.info("the Actual UserName sign With is = "+Utility.getText(driver,LoginInAsLocator));
        LogsUtils.info("the Expecteds UserName sign With is = "+ExepectedUserName);
        return Utility.getText(driver,LoginInAsLocator).equals(ExepectedUserName);
    }
    public Boolean VerifyThatWeArriveToSubscriptionAreaCorrectly(String ExpectedWord)
    {
        return Utility.getText(driver,SubscriptionMessageLocator).equals(ExpectedWord);
    }
    public P01_HomePage SendSubscriptionEmail(String Email)
    {
        Utility.sendData(driver,SubscriptionEmailLocator,Email);
        return this;
    }
    public P01_HomePage ClickOnSubscriptionButton()
    {
        Utility.clickingOnElement(driver,SubscriptionButtonLocator);
        return this;
    }
    public Boolean VerifyThatWeSubscribeCorrectly(String ExpectedWord)
    {
        return Utility.getText(driver,alertSuccessSubscribeLocator).equals(ExpectedWord);
    }
    public P01_HomePage ScrollingInHomePage()
    {
        Utility.scrolling(driver,SubscriptionEmailLocator);
        return this;
    }
    public P10_CartPage ClickOnCartButton()
    {
        Utility.clickingOnElement(driver,CartButtonLocator);
        return new P10_CartPage(driver);
    }

}
