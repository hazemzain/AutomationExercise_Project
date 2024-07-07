package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P12_PaymentDonePage {
    private final By OrderPlacedLocator= By.xpath("(//div/p)[1]");
    private final WebDriver driver;

    public P12_PaymentDonePage(WebDriver driver) {
        this.driver=driver;
    }
    public Boolean VerifyThatTheOrderPlace(String ExpectedString)
    {

        return Utility.getText(driver,OrderPlacedLocator).equals(ExpectedString);
    }
}
