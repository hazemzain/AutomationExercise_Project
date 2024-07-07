package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P09_ProductDetailsPage {
    private final By NameOfProductLocator= By.xpath("//div[@class='product-information']/h2");
    private final By CategoryOfProductLocator=By.xpath("//div[@class='product-information']/p");
    private final By PriceOfProductLocator=By.xpath("//span/span");
    private final By EnterNumberOfQuantityLocator=By.id("quantity");
    private final By AddToCartButton=By.xpath("//span/button[@type]");
    private final By ContinueShoppingButtonLocator=By.xpath("//button[@data-dismiss]");
    private final By ViewCartButton=By.xpath("//p/a");
    private final By NameReviewLocator=By.id("name");
    private final By EmailReviewLocator=By.id("email");
    private final By ReviewMessageLocator=By.xpath("//textarea[@id='review']");
    private final By SubmitReviewButtonLocator=By.id("button-review");
    private final By successMessageForReviewLocator=By.cssSelector("div[class='alert-success alert'] span");
    private final WebDriver driver;

    public P09_ProductDetailsPage(WebDriver driver) {
        this.driver=driver;
    }
    public Boolean verifyThatNameOfProductIsVisible()
    {
      return driver.findElement(NameOfProductLocator).isDisplayed();

    }
    public P09_ProductDetailsPage EnterNumberOfQuantityForProduct(String QuantityNumber)
    {
        driver.findElement(EnterNumberOfQuantityLocator).clear();
        Utility.sendData(driver,EnterNumberOfQuantityLocator,QuantityNumber);
        return this;
    }
    public P09_ProductDetailsPage EnterFullReviewDataForProduct(String Name,String Email,String Message)
    {
        Utility.sendData(driver,NameReviewLocator,Name);
        Utility.sendData(driver,EmailReviewLocator,Email);
        Utility.sendData(driver,ReviewMessageLocator,Message);
        return this;
    }
    public P09_ProductDetailsPage ClickSubmitReviewButton()
    {
        Utility.clickingOnElement(driver,SubmitReviewButtonLocator);
        return this;
    }
    public Boolean VerifyThatReviewMessageSubmitCorrectly(String ExpectedWord)
    {
        return Utility.getText(driver,successMessageForReviewLocator).equals(ExpectedWord);
    }
    public P09_ProductDetailsPage ClickInAddToCartButton()
    {
        Utility.clickingOnElement(driver,AddToCartButton);
        return this;
    }
    public P09_ProductDetailsPage ClickInContinueButton()
    {
        Utility.clickingOnElement(driver,ContinueShoppingButtonLocator);

        return this;
    }
    public P10_CartPage ClickOnViewCartButton()
    {
        Utility.clickingOnElement(driver,ViewCartButton);
        return new P10_CartPage(driver);
    }
    public Boolean verifyThatCategoryOfProductIsVisible()
    {
        return driver.findElement(CategoryOfProductLocator).isDisplayed();

    }
    public Boolean verifyThatPriceOfProductLocatorIsVisible()
    {
        return driver.findElement(PriceOfProductLocator).isDisplayed();

    }
    public Boolean VerifyURLAssertion(WebDriver driver,String expectedURL)
    {
        return driver.getCurrentUrl().equals(expectedURL);
    }

}
