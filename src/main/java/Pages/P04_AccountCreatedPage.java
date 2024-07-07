package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_AccountCreatedPage {
    private final WebDriver driver;
    private final By ContinueButton= By.xpath("//a[@data-qa='continue-button']");

    public P04_AccountCreatedPage(WebDriver driver) {
        this.driver=driver;
    }
    public P01_HomePage ClickOnContinueButton()
    {
        Utility.clickingOnElement(driver,ContinueButton);
        return  new P01_HomePage(driver);
    }
}
