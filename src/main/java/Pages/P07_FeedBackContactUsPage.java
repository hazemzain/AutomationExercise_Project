package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P07_FeedBackContactUsPage {
    private final WebDriver driver;
    private  final By ContactUsFeedbackMessageLocator= By.xpath("//*[@id=\"contact-page\"]/div[2]/div[1]/div/div[2]");
    private final By GoToHomePageButton=By.xpath("//*[@id=\"form-section\"]/a");

    public P07_FeedBackContactUsPage(WebDriver driver) {
        this.driver=driver;
    }
    public P01_HomePage ClickOnGoHomeButton()
    {
        Utility.clickingOnElement(driver,GoToHomePageButton);
        return new P01_HomePage(driver);
    }
    public Boolean VerifyThatContactInformationIsSubmittedCorrectly(String ExepectedMessage)
    {
        return Utility.getText(driver,ContactUsFeedbackMessageLocator).equals(ExepectedMessage);
    }

}
