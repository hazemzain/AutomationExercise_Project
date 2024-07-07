package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Month;

public class P03_RegisterPage {
    private final WebDriver driver;
    private final By PasswordFieldLocator= By.id("password");
    private final By DaysDropdownLocator= By.id("days");
    private final By MonthDropdownLocator= By.id("months");
    private final By YearDropdownLocator= By.id("years");
    private final By NewsletterLocator=By.id("newsletter");
    private final By SpecialOfferLocator=By.id("optin");
    private final By FirstNameLocator=By.id("first_name");
    private final By LastNameLocator=By.id("last_name");
    private final By CompanyNameLocator=By.id("company");
    private final By AddressLocator=By.id("address1");
    private final By CountryDropdownLocator= By.id("country");
    private final By StateLocator=By.id("state");
    private final By CityLocator=By.id("city");
    private final By ZipCodeLocator=By.id("zipcode");
    private final By MobileNumberLocator=By.id("mobile_number");
    private final By CreateAccountButtonLocator=By.xpath("//button[@data-qa='create-account']");
    private final By AccountCreatedLocator=By.xpath("//h2[@data-qa='account-created']");


public P03_RegisterPage FullRegistrationInformationForNewUser(String Password,String Day,String Month,String Year,
                                                              String FirstName,String LastName,
                                                              String Company,String Address,
                                                              String Country,String State,
                                                              String City,
                                                              String ZipCode,String MobileNumber)
{
    Utility.sendData(driver,PasswordFieldLocator,Password);
    LogsUtils.info("New Password = "+Password);
    Utility.selectingFromDropDown(driver,DaysDropdownLocator,Day);
    LogsUtils.info("Day is = "+Day);
    Utility.selectingFromDropDown(driver,MonthDropdownLocator, Month);
    LogsUtils.info("Month is = "+Month);
    Utility.selectingFromDropDown(driver,YearDropdownLocator,Year);
    LogsUtils.info("Year is = "+Year);
    Utility.clickingOnElement(driver,NewsletterLocator);
    Utility.clickingOnElement(driver,SpecialOfferLocator);
    Utility.sendData(driver,FirstNameLocator,FirstName);
    LogsUtils.info("FirstName is = "+FirstName);
    Utility.sendData(driver,LastNameLocator,LastName);
    LogsUtils.info("LastName is = "+LastName);
    Utility.sendData(driver,CompanyNameLocator,Company);
    LogsUtils.info("Company is = "+Company);
    Utility.sendData(driver,AddressLocator,Address);
    LogsUtils.info("Address is = "+Address);
    Utility.selectingFromDropDown(driver,CountryDropdownLocator, Country);
    LogsUtils.info("Country is = "+Country);
    Utility.sendData(driver,StateLocator,State);
    LogsUtils.info("State is = "+State);
    Utility.sendData(driver,CityLocator,City);
    LogsUtils.info("City is = "+City);
    Utility.sendData(driver,ZipCodeLocator,ZipCode);
    LogsUtils.info("ZipCode is = "+ZipCode);
    Utility.sendData(driver,MobileNumberLocator,MobileNumber);
    LogsUtils.info("MobileNumber is = "+MobileNumber);
    return this;
}
public P04_AccountCreatedPage ClickOnCreatingAccountButton()
{
    Utility.clickingOnElement(driver,CreateAccountButtonLocator);
    return new P04_AccountCreatedPage(driver) ;
}
public  Boolean VerifyCreatedAccount(WebDriver driver,String expectedURL)
{
    LogsUtils.info("the Actual text is = "+Utility.getText(driver,AccountCreatedLocator));
    LogsUtils.info("the Expecteds text is = "+expectedURL);
    return Utility.getText(driver,AccountCreatedLocator).equals(expectedURL);
}




    public P03_RegisterPage(WebDriver driver) {
        this.driver=driver;
    }
}
