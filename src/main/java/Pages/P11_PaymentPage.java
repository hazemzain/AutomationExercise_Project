package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P11_PaymentPage {
    private final By NameOnCardLocator= By.xpath("//input[@name='name_on_card']");
    private final By CardNumberLocator=By.xpath("//input[@name='card_number']");
    private final By CvcLocator=By.xpath("//input[@name='cvc']");
    private final By ExpirationMonthLocator=By.xpath("//input[@name='expiry_month']");
    private final By ExpirationYearLocator=By.xpath("//input[@name='expiry_year']");
    private final By PayAndConfirmOrderButtonLocator=By.id("submit");
    private final WebDriver driver;

    public P11_PaymentPage(WebDriver driver) {
        this.driver=driver;
    }
    public Boolean VerifyURLAssertion(WebDriver driver,String expectedURL)
    {
        return driver.getCurrentUrl().equals(expectedURL);
    }
    public P11_PaymentPage FullPaymentInformationForOrder(String NameOnCard,String CardNumber,
                                                          String Cvc,String ExpirationMonth,
                                                          String ExpirationYear)
    {
        Utility.sendData(driver,NameOnCardLocator,NameOnCard);
        LogsUtils.info("NameOnCard = "+NameOnCard);
        Utility.sendData(driver,CardNumberLocator,CardNumber);
        LogsUtils.info("CardNumber = "+CardNumber);
        Utility.sendData(driver,CvcLocator,Cvc);
        LogsUtils.info("Cvc = "+Cvc);
        Utility.sendData(driver,ExpirationMonthLocator,ExpirationMonth);
        LogsUtils.info("ExpirationMonth = "+ExpirationMonth);
        Utility.sendData(driver,ExpirationYearLocator,ExpirationYear);
        LogsUtils.info("ExpirationYear = "+ExpirationYear);
        return this;
    }
    public P12_PaymentDonePage ClickOnPayAndConfirmOrderButton()
    {
        Utility.clickingOnElement(driver,PayAndConfirmOrderButtonLocator);
        return new P12_PaymentDonePage(driver);
    }

}
