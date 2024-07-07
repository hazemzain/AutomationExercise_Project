package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class P06_ContactUsPage {
    private final By NameFieldContactUsLocator= By.xpath("//div/input[@name='name']");
    private final By EmailFieldContactUsLocator= By.xpath("//div/input[@name='email']");
    private final By SubjectFieldContactUsLocator= By.xpath("//div/input[@name='subject']");
    private final By MessageFieldContactUsLocator=By.id("message");
    private final By ChooseFileContactUsLocator=By.xpath("//input[@type='file']");
    private final By SubmitContactUsButtonLocator=By.xpath("//input[@type='submit']");

    private final WebDriver driver;

    public P06_ContactUsPage(WebDriver driver) {
        this.driver=driver;
    }
    public P06_ContactUsPage FullInformationOfContactUsForm(String Name,String Email,
                                                            String Subject,String Message)
    {
        Utility.sendData(driver,NameFieldContactUsLocator,Name);
        LogsUtils.info("The Name for Contact Us Form = "+Name);
        Utility.sendData(driver,EmailFieldContactUsLocator,Email);
        LogsUtils.info("The Email for Contact Us Form = "+Email);
        Utility.sendData(driver,SubjectFieldContactUsLocator,Subject);
        LogsUtils.info("The Subject for Contact Us Form = "+Subject);
        Utility.sendData(driver,MessageFieldContactUsLocator,Message);
        LogsUtils.info("The Message for Contact Us Form = "+Message);
        //Send File to Choose file button in Contact Form
        driver.findElement(ChooseFileContactUsLocator).sendKeys("D:\\4 year eng\\Automation_Excercise_Website_Project\\Automation_Excercise_Website_Project\\src\\test\\resources\\TestData\\ContactUsFile.txt");


        return this;
    }
    public P07_FeedBackContactUsPage ClickSubmitButton()
    {
        Utility.clickingOnElement(driver,SubmitContactUsButtonLocator);
        return new P07_FeedBackContactUsPage(driver);
    }
}
