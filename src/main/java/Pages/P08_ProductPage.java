package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.management.loading.PrivateClassLoader;
import java.util.List;

public class P08_ProductPage {
    private final By ViewButtonForFirstProduct= By.xpath("//li/a[@href='/product_details/1']");
    private final By AllProductViewerLocator=By.xpath("//div/h2[@class='title text-center']");
    private final By NameOfAllSearchProductLocator=By.xpath("//div[@class='productinfo text-center']/p");
    private final By InputSearchLocator=By.xpath("//input[@name='search']");
    private final By SearchButtonLocator=By.xpath("//button[@type='button']");
    private final By SearchedProductLocator=By.xpath("//div[@class='features_items']/h2");
    private final By AddToCartButtonFirstProductLocator=By.xpath("(//div/a[@data-product-id='1'])[1]");
    private final By AddToCartButtonSecondProductLocator=By.xpath("(//div/a[@data-product-id='2'])[1]");
    private final By ContinueShoppingButtonLocator=By.xpath("//button[@data-dismiss]");
    private final By ViewCartButton=By.xpath("//p/a");
    private final By WomenDressTextLocator=By.xpath("//div[@class='features_items']/h2");
    private final By MenCategoryButtonLocator=By.xpath("//a[@href='#Men' ]/span/i");
    private final By TshirtsInMenCategoryLocator=By.xpath("//a[@href='/category_products/3' ]");
    private final By MenTshirtTextLocator=By.xpath("//div[@class='features_items']/h2");
    private final WebDriver driver;

    public P08_ProductPage(WebDriver driver) {
        this.driver=driver;
    }
    public P09_ProductDetailsPage ClickOnViewButtonForFirstProduct()
    {
        Utility.clickingOnElement(driver,ViewButtonForFirstProduct);
        return new P09_ProductDetailsPage(driver);
    }
    public P08_ProductPage ClickInTshirtButtonInMenCategory()
    {
        Utility.clickingOnElement(driver,MenCategoryButtonLocator);
        Utility.clickingOnElement(driver,TshirtsInMenCategoryLocator);
        return new P08_ProductPage(driver);
    }
    public P08_ProductPage AddProductOneToCart()
    {
       Utility.clickingOnElement(driver,AddToCartButtonFirstProductLocator);

        return this;
    }
    public Boolean VerifyWomanDressCategory(String ExpectedWord)
    {
        LogsUtils.info("The Actual Word IS = "+Utility.getText(driver,WomenDressTextLocator));
        LogsUtils.info("The Expected Word Is = "+ExpectedWord);
        return Utility.getText(driver,WomenDressTextLocator).equals(ExpectedWord);
    }
    public Boolean VerifyMenTshirtCategory(String ExpectedWord)
    {
        LogsUtils.info("The Actual Word IS = "+Utility.getText(driver,MenTshirtTextLocator));
        LogsUtils.info("The Expected Word Is = "+ExpectedWord);
        return Utility.getText(driver,MenTshirtTextLocator).equals(ExpectedWord);
    }
    public P08_ProductPage AddProductTwoToCart()
    {
        Utility.clickingOnElement(driver,AddToCartButtonSecondProductLocator);

        return this;
    }
    public P08_ProductPage ClickInContinueButton()
    {
        Utility.clickingOnElement(driver,ContinueShoppingButtonLocator);

        return this;
    }
    public P10_CartPage ClickOnViewCartButton()
    {
        Utility.clickingOnElement(driver,ViewCartButton);
        return new P10_CartPage(driver);
    }
    public P08_ProductPage InputSearchText(String SearchInput)
    {
        Utility.sendData(driver,InputSearchLocator,SearchInput);
        return this;
    }
    public P08_ProductPage ClickInSearchButton()
    {
        Utility.clickingOnElement(driver,SearchButtonLocator);
        return this;
    }
    public boolean VerifyThatAllProductIsVisible(String ExcepectedString)
    {
        LogsUtils.info(Utility.getText(driver,AllProductViewerLocator));
        return Utility.getText(driver,AllProductViewerLocator).equals(ExcepectedString);
    }
    public List<WebElement> AllProductNameAfterSearch()
    {
        return driver.findElements(NameOfAllSearchProductLocator);
    }
    public Boolean VerifyThatSearchedProductsIsVisible(String ExcepectedString)
    {
        return Utility.getText(driver,SearchedProductLocator).equals(ExcepectedString);
    }
    public P08_ProductPage AddAllProductToCart()
    {
        List<WebElement>Products=driver.findElements(By.xpath("//div/a[@data-product-id]"));
        int NumberOfProduct=10;
        for(int i=1;i<=NumberOfProduct;i++)
        {
            if(i!=9&&i!=10) {
                By AddToCartButtonFirstProductLocator = By.xpath("(//div/a[@data-product-id='" + i + "'])[1]");
                Utility.clickingOnElement(driver, AddToCartButtonFirstProductLocator);
                ClickInContinueButton();
            }


        }
        return this;
    }
    public P08_ProductPage AddAllProductToCartV2()
    {
        List<WebElement>Products=driver.findElements(By.xpath("//a[@class='btn btn-default add-to-cart']"));

        for(int i=1;i<=Products.size();i=i+2)
        {

                By AddToCartButtonFirstProductLocator = By.xpath("(//a[@class='btn btn-default add-to-cart'])["+ i +"]");
                Utility.clickingOnElement(driver, AddToCartButtonFirstProductLocator);
                ClickInContinueButton();



        }
        return this;
    }

}
