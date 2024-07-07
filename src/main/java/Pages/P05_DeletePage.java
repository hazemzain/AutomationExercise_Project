package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_DeletePage {
    private final WebDriver driver;
    private final By ContinueButton= By.xpath("//a[@data-qa='continue-button']");
    private final By AccountDeletedLocator=By.xpath("//h2[@data-qa='account-deleted']");
    public P05_DeletePage(WebDriver driver) {
        this.driver=driver;
    }
    public P01_HomePage ClickOnContinueButton()
    {
        Utility.clickingOnElement(driver,ContinueButton);
        return  new P01_HomePage(driver);
    }
    public  Boolean VerifyCreatedAccount(WebDriver driver,String expectedURL)
    {
        LogsUtils.info("the Actual text is = "+Utility.getText(driver,AccountDeletedLocator));
        LogsUtils.info("the Expecteds text is = "+expectedURL);
        return Utility.getText(driver,AccountDeletedLocator).equals(expectedURL);
    }
}
