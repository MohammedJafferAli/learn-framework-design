package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> productTitles;

    @FindBy(css = ".totalRow button")
    WebElement btnCheckOut;

    public Boolean verifyCartProducts(String productName) {
        //Verify added product available in MyCart
        List<WebElement> cartProducts = productTitles;
        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckOutPage goToCheckOut() {
        btnCheckOut.click();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        return checkOutPage;

    }
}
