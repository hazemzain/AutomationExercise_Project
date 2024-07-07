package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P10_CartPage {
    private final By SubscriptionMessageLocator=By.xpath("//div[@class='single-widget']/h2");
    private final By SubscriptionEmailLocator=By.id("susbscribe_email");
    private final By SubscriptionButtonLocator=By.id("subscribe");
    private final By alertSuccessSubscribeLocator=By.id("success-subscribe");
    private final By NumberOfItemInCart=By.xpath("//td[@class='cart_product']");
    private final By ProceedToCheckOutButtonLocator=By.xpath("//div[@class='col-sm-6']/a");
    private final By LoginCheckOutButtonLocator=By.xpath("//p/a[@href='/login']");
    private final By PlaceOrderButtonLocator=By.xpath("//a[@href='/payment']");
    private final By CartEmptyLocator=By.xpath("//p[@class='text-center']/b");
    private final WebDriver driver;

    public P10_CartPage(WebDriver driver) {
        this.driver=driver;
    }
    public int NumberOfItemInCart()
    {
        List<WebElement> ItemList=driver.findElements(NumberOfItemInCart);
        return ItemList.size();
    }
    public Boolean VerifyURLAssertion(WebDriver driver,String expectedURL)
    {
        return driver.getCurrentUrl().equals(expectedURL);
    }
    public Boolean VerifyThatWeArriveToSubscriptionAreaCorrectly(String ExpectedWord)
    {
        return Utility.getText(driver,SubscriptionMessageLocator).equals(ExpectedWord);
    }
    public P10_CartPage SendSubscriptionEmail(String Email)
    {
        Utility.sendData(driver,SubscriptionEmailLocator,Email);
        return this;
    }
    public P10_CartPage ClickOnSubscriptionButton()
    {
        Utility.clickingOnElement(driver,SubscriptionButtonLocator);
        return this;
    }
    public P10_CartPage ClickOnProceedToCheckOutButton()
    {
        Utility.clickingOnElement(driver,ProceedToCheckOutButtonLocator);
        return this;
    }
    public P10_CartPage RemoveAllProductInTheCart()
    {
        List<WebElement>ProductItemInTheCart=driver.findElements(By.xpath("//a[@class='cart_quantity_delete']"));
        for(int i=ProductItemInTheCart.size();i>=1;i--)
        {
            //int i=1;i<ProductItemInTheCart.size()+1;i++
            By XDeleteButtonForOneProduct=By.xpath("(//a[@class='cart_quantity_delete'])["+i+"]");
            LogsUtils.info("Romve Item Number "+i+"From cart");
            Utility.clickingOnElement(driver,XDeleteButtonForOneProduct);
        }
        return this;
    }
    public P02_LoginPage ClickOnRegisterOrSignInCheckOutButton()
    {
        Utility.clickingOnElement(driver,LoginCheckOutButtonLocator);
        return new P02_LoginPage(driver);
    }
    public Boolean VerifyThatWeSubscribeCorrectly(String ExpectedWord)
    {
        return Utility.getText(driver,alertSuccessSubscribeLocator).equals(ExpectedWord);
    }
    public Boolean VerifyThatTheCartIsEmpty(String ExpectedWord)
    {
        LogsUtils.info("Expected Word Is = "+ExpectedWord);
        LogsUtils.info("Actual Word Is = "+Utility.getText(driver,CartEmptyLocator));
        return Utility.getText(driver,CartEmptyLocator).equals(ExpectedWord);
    }
    public String GetPriceForOneProduct(int NumberOfProduct)
    {
        By PriceForProduct=By.xpath("(//td[@class='cart_price']/p)"+"["+NumberOfProduct+"]");
        String Price=Utility.getText(driver,PriceForProduct).replace("Rs. ","");
        LogsUtils.info("The Price For product= "+Price);
        return Price;
    }
    public String GetTotalPriceForOneProduct(int NumberOfProduct)
    {
        By TotalPriceForProduct=By.xpath("(//td[@class='cart_total']/p)"+"["+NumberOfProduct+"]");
        String Price=Utility.getText(driver,TotalPriceForProduct).replace("Rs. ","");
        LogsUtils.info("The Total Price For product= "+Price);
        return Price;
    }
    public Boolean ComparingTotalPriceWithQuantityAndPriceForProduct(int i)
    {
        String TotalPriceForProduct=GetTotalPriceForOneProduct(i);
        String PriceForProduct=GetPriceForOneProduct(i);
        String QuantityForProduct=GetQuantityForOneProduct(i);
        int CalculateTotalPrice=Integer.parseInt(PriceForProduct)*Integer.parseInt(QuantityForProduct);
        String CalculatedTotalPrice=String.valueOf(CalculateTotalPrice);
        return TotalPriceForProduct.equals(CalculatedTotalPrice);
    }
    public String GetQuantityForOneProduct(int NumberOfProduct)
    {
        By QuantityForProduct=By.xpath("((//td[@class='cart_quantity']/button))"+"["+NumberOfProduct+"]");
        String Quantity=Utility.getText(driver,QuantityForProduct);
        LogsUtils.info("The Quantity For product= "+Quantity);
        return Quantity;
    }
    public P10_CartPage ScrollingInCartPage()
    {
        Utility.scrolling(driver,SubscriptionEmailLocator);
        return this;
    }
    public P11_PaymentPage ClickOnPlaceOrderButton()
    {
        Utility.clickingOnElement(driver,PlaceOrderButtonLocator);
        return new P11_PaymentPage(driver);
    }
}
