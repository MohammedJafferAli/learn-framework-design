package pageobjects;

import abstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckOutPage extends AbstractComponents {

    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement inpCountryName;

    @FindBy(css = ".ta-item")
    List<WebElement> suggestedCountryList;
    @FindBy(css = ".action__submit")
    WebElement btnPlaceOrder;

    By ddcountryList = By.cssSelector(".ta-results");

    public void selectCountry(String countryName) {
        Actions customAction = new Actions(driver);
        customAction.sendKeys(inpCountryName, countryName).build().perform();
        waitForElementToAppear(ddcountryList);

        //Iterate through the list, find the country & select

        for (WebElement e : suggestedCountryList) {
            if (e.getText().equalsIgnoreCase(countryName)) {
                e.click();
            }
        }
    }

    public ConfirmationPage placeOrder() {
        btnPlaceOrder.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }
}
